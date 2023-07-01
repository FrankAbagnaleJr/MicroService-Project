package com.kyrie.order.sercice;

import com.kyrie.order.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    Order getById(@Param("id") Long id);
}
