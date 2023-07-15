package com.kyrie.order.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyrie.base.model.PageParams;
import com.kyrie.order.clients.OrderClient;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.mapper.OrderMapper;
import com.kyrie.order.pojo.Order;
import com.kyrie.order.pojo.User;
import com.kyrie.order.sercice.IOrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderClient orderClient;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public IPage<Order> queryOrderList(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto) {
        Page<Order> ipage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());

        LambdaQueryWrapper lqw = new LambdaQueryWrapper<>();

        Page<Order> page = orderMapper.selectPage(ipage, lqw);

        page.getRecords().stream().forEach(o -> {
            o.setUser(orderClient.selectById(o.getUserId()));
        });

        return page;
    }

    @Override
    public Order queryById(Long id) {

        Order order = null;

        //先从缓存中查，查到直接返回
        order = (Order) redisTemplate.opsForValue().get("order:" + id);
        if (order != null) return order;

        //缓存没有，查数据库，查数据库得先加锁，不然访问都打到数据库，会redis击穿
        //先得到redisson锁
        RLock orderlock = redissonClient.getLock("orderlock");
        try {
            //尝试加锁，不会自动续期
//            boolean islock = orderlock.tryLock(1, 10, TimeUnit.SECONDS);

            //尝试加锁，会自动续期
            boolean islock = orderlock.tryLock();
            //没得到锁，自旋重试
            if (!islock) {
                Thread.sleep(100);
                return queryById(id);
            }

            //得到锁，查询数据库
            order = orderMapper.selectById(id);
            //都没有就缓存个空值防止缓存穿透
            if (order == null) {
                redisTemplate.opsForValue().set("order:" + id, null, new Random().nextInt(10) + 1, TimeUnit.SECONDS);
                return null;
            }

            //查询到订单封装用户对象
            Long userId = order.getUserId();
            User user = orderClient.selectById(userId);
            order.setUser(user);

            //数据库查到了，回写redis并返回
            redisTemplate.opsForValue().set("order:"+id,order,new Random().nextInt(10)+1,TimeUnit.SECONDS);
            return order;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //解锁
            orderlock.unlock();
        }
    }
}
