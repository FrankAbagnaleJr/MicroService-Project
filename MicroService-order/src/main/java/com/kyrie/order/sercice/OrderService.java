package com.kyrie.order.sercice;

import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    PageResult<Order> list(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto);

    Order getById(Long id);
}
