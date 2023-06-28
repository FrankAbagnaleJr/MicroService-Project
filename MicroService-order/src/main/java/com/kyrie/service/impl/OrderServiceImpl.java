package com.kyrie.service.impl;

import com.kyrie.clients.UserClient;
import com.kyrie.pojo.Order;
import com.kyrie.pojo.User;
import com.kyrie.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderService orderService;

    @Autowired
    UserClient userClient;
    @Override
    public Order getById(Long id) {
        Order order = orderService.getById(id);
        User user = userClient.getUserById(order.getId());
        order.setUser(user);
        return order;
    }
}
