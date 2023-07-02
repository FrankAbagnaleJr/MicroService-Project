package com.kyrie.order.controller;

import com.kyrie.order.pojo.Order;
import com.kyrie.order.sercice.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:09 周日
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单接口 value",tags = "订单接口 tags")
public class OrderContraller {
    @Autowired
    OrderService orderService;

    @ApiOperation("根据订单号查询订单")
    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Long id) {
        return orderService.getById(id);
    }
}
