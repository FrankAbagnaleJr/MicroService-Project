package com.kyrie.service.impl;

import com.kyrie.mapper.UserMapper;
import com.kyrie.pojo.User;
import com.kyrie.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }
}
