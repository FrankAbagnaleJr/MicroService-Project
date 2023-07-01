package com.kyrie.order.sercice.impl;

import com.kyrie.order.clients.OrderClient;
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
    public Order getById(Long id) {
        Order order = orderMapper.getById(id);
        User user = orderClient.getUserById(order.getUserId());
        order.setUser(user);
        return order;
    }
}
