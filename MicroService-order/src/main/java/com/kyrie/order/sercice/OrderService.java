package com.kyrie.order.sercice;

import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.pojo.Order;

public interface OrderService {
    /**
     *
     * @param page
     * @param dto
     * @return
     */
    PageResult<Order> queryOrderList(PageParams page, QueryOrderParamsDto dto);

    Order getById(Long id);
}
