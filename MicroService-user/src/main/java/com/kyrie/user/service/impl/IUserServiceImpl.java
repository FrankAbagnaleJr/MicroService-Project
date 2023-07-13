package com.kyrie.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyrie.base.model.PageParams;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.mapper.UserMapper;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.IUserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String RED_USER = "user:";
    private final String LOCK = "lock";

    @Override
    public IPage queryUserList(PageParams pageParams, @RequestBody(required = false) QueryUserParamsDto queryUserParamsDto) {
        Page page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper();
        return userMapper.selectPage(page, lqw);
    }

    @Override
    public User queryById(Long id) {

        User user = null;

        //先从redis中查，查到了直接返回
        user = (User) redisTemplate.opsForValue().get(RED_USER + id);
        if (user != null) return user;

        // 获取Redisson锁（可重入），指定锁的名称
        RLock redissonClientLockLock = redissonClient.getLock("userlock");

        try {
            //尝试获取锁，参数是：最大等待时间（期间回重试），锁自动释放时间，时间单位
            boolean islock = redissonClientLockLock.tryLock(1, 10, TimeUnit.SECONDS);

            //没拿到锁，自旋
            if (!islock) {
                Thread.sleep(100);
                return queryById(id);
            }

            // 得到redisson锁开始从数据库查询
            user = userMapper.selectById(id);

            //数据库查到了，再查redis，防止拿锁时已经有线程回写redis了
            if (user != null) {
                //再查redis
                User user2 = (User) redisTemplate.opsForValue().get(RED_USER + id);
                //这时候说明加锁前已经被别的线程缓存redis了，不需要再回写，直接返回redis中的数据
                if (user2 != null) return user2;

                //这时候说明加锁后redis确定没有数据，可以回写
                redisTemplate.opsForValue().set(RED_USER + id, user, new Random().nextInt(10) + 1, TimeUnit.SECONDS);

                //返回数据库查询到的对象
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //解锁
            redissonClientLockLock.unlock();
        }

        //缓存穿透 - redis跟数据库都没，回写redis个空值，防止缓存穿透
        redisTemplate.opsForValue().set(RED_USER, null, new Random().nextInt(10) + 1, TimeUnit.SECONDS);
        return null;
    }

    public User queryById2(Long id) {     //没用redisson的加锁方式，锁没有超时自动续期

        User user = null;

        //先从redis中查，查到了直接返回
        user = (User) redisTemplate.opsForValue().get(RED_USER + id);
        if (user != null) return user;

        String uuid = UUID.randomUUID().toString();
        // TODO 缓存击穿 - redis中没有从数据库查，查数据库时先加锁，防止缓存击穿,添加分布式锁（加锁解锁都要保证原子性）
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid,new Random().nextInt(10)+1,TimeUnit.SECONDS);
        //没得到锁就自旋
        if (!lock) {
            //休眠100毫秒后自旋
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //自旋
            return queryById(id);
        }

        // TODO 得到分布式锁开始从数据库查询
        user = userMapper.selectById(id);

        //数据库查到了，再查redis，防止拿锁时已经有线程回写redis了
        if (user != null) {

            User user2 = (User) redisTemplate.opsForValue().get(RED_USER + id);
            //这时候说明加锁前已经被别的线程缓存redis了，不需要再回写，直接返回redis中的数据
            if (user2 != null) return user2;

            //这时候说明加锁后redis确定没有数据，可以回写
            redisTemplate.opsForValue().set(RED_USER + id, user, new Random().nextInt(10) + 1, TimeUnit.SECONDS);

            // TODO 删除分布式锁,先判断是不是自己加的锁，是就删，不是就不用管，判断+删除java代码不具备原子性，用lua脚本实现原子性（加锁解锁都要保证原子性）
            //String uuidLock = (String) redisTemplate.opsForValue().get(uuid);
            //if (uuid.equals(uuidLock)) redisTemplate.delete("lock");
            // 上面两行代码不具备原子性的，万一判断true，（在删除的中间锁过期了，别的线程拿到锁），这时候再执行删除又把别人的锁删了

            //lua脚本
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1]" +
                    "then" +
                    "    return redis.call('del',KEYS[1])" +
                    "else" +
                    "    return 0" +
                    "end";
            //判锁断+删除锁 （原子性）。可以放在finally里面
            redisTemplate.execute(
                    new DefaultRedisScript<Long>(script,Long.class),         //lua脚本，脚本返回值类型。Long是返回值类型
                    Arrays.asList("lock"),                                   //数组，里面都是key
                    uuid);                                                   //value

            //返回数据库查询到的对象
            return user;
        }

        // TODO 缓存穿透 - redis跟数据库都没，回写redis个空值，防止缓存穿透
        redisTemplate.opsForValue().set(RED_USER, null, new Random().nextInt(10) + 1, TimeUnit.SECONDS);
        return null;
    }
}
