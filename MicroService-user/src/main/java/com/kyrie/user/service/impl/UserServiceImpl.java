package com.kyrie.user.service.impl;

import com.kyrie.base.model.PageParams;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.mapper.UserMapper;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> queryUserList(PageParams page, QueryUserParamsDto dto) {

        return null;
    }
}
