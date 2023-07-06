package com.kyrie.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.mapper.UserMapper;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:47 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Page<User> queryUserList(PageParams pageParams, QueryUserParamsDto queryUserParamsDto) {
        Page<User> page = new Page<>(pageParams.getPageNum(),pageParams.getPageSize());
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>(); // TODO 未做查询条件
        Page<User> pageResult = userMapper.selectPage(page, lqw);
        return pageResult;
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }


}
