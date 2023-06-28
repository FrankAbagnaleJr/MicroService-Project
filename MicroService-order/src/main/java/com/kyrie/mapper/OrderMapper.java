package com.kyrie.mapper;

import com.kyrie.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper{
    @Select("select * form tb_order where id = #{id}")
    Order getById(@Param("id") Long id);
}
