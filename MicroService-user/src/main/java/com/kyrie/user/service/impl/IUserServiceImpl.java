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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    private final String RED_USER = "user:";

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

        // TODO 缓存击穿 - redis中没有从数据库查，查数据库时先加锁，防止缓存击穿,分布式锁
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "1");
        //没得到锁就自旋
        if (!lock) {
            //休眠100毫秒

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
            //删除分布式锁
            redisTemplate.delete("lock");
            //返回对象
            return user;
        }


        // TODO 缓存穿透 - redis跟数据库都没，回写redis个空值，防止缓存穿透
        redisTemplate.opsForValue().set(RED_USER, null, new Random().nextInt(10) + 1, TimeUnit.SECONDS);
        return user;
    }
}
