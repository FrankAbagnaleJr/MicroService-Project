package com.kyrie.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kyrie.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper {
}
