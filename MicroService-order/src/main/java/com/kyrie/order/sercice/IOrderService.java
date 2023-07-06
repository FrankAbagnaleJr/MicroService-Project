package com.kyrie.order.sercice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kyrie.base.model.PageParams;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.pojo.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService extends IService<Order> {
    IPage queryOrderList(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto);
}
