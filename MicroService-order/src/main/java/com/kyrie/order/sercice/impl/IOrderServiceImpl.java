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
import com.kyrie.order.sercice.IOrderService;
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

    @Override
    public IPage<Order> queryOrderList(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto) {
        Page<Order> ipage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper<>();
        Page<Order> page = orderMapper.selectPage(ipage, lqw);
        page.getRecords().stream().forEach(o -> {o.setUser(orderClient.selectById(o.getUserId()));});
        return page;
    }

    @Override
    public Order queryById(Long id) {

//        Order order = null;
//
//        //先从缓存中查，查到直接返回
//        order = (Order) redisTemplate.opsForValue().get("order:" + id);
//        if (order!=null) return order;
//
//        //缓存没有，查数据库
//        order = orderMapper.selectById(id);
//        if (order == null) {
//            //都没有就缓存个空值防止缓存穿透
//            redisTemplate.opsForValue().set("order:"+id,null,new Random().nextInt(10)+1, TimeUnit.SECONDS);
//        }

        return null;
    }
}
