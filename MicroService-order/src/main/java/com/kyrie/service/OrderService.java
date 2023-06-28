package com.kyrie.service;

import com.kyrie.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderService {
    Order getById(@Param("id") Long id);
}
