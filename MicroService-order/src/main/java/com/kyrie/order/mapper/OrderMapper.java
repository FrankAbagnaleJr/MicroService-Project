package com.kyrie.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyrie.order.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
