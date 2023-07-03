package com.kyrie.order.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.order.clients.OrderClient;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.mapper.OrderMapper;
import com.kyrie.order.pojo.Order;
import com.kyrie.order.pojo.User;
import com.kyrie.order.sercice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:05 周日
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderClient orderClient;

    @Override
    public PageResult<Order> list(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto) {
        Page<Order> page = new Page<>(pageParams.getPageNum(),pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper<>();
        Page page1 = orderMapper.selectPage(page, lqw);

        PageResult<Order> orderPageResult = new PageResult<>();
        orderPageResult.setPage(page1.getPages());
        orderPageResult.setPageSize(page1.getSize());
        orderPageResult.setCounts(page1.getTotal());
        orderPageResult.setItems(page1.getRecords());
        return orderPageResult;
    }

    @Override
    public Order getById(Long id) {
        Order order = orderMapper.selectById(id);
        order.setUser(orderClient.getUserById(order.getUserId()));
        return order;
    }
}
