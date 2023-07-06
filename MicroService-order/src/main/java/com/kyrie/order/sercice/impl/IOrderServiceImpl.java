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
import org.springframework.stereotype.Service;

@Service
public class IOrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderClient orderClient;

    @Override
    public IPage<Order> queryOrderList(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto) {
        Page<Order> ipage = new Page<>(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper<>();
        Page<Order> page = orderMapper.selectPage(ipage, lqw);
        page.getRecords().stream().forEach(o -> {o.setUser(orderClient.selectById(o.getUserId()));});
        return page;
    }
}
