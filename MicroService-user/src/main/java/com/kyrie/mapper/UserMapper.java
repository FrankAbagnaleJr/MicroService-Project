package com.kyrie.mapper;

import com.kyrie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * form tb_user where id = #{id}")
    User selectUserById(@Param("id") Long id);
}
