package com.kyrie.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kyrie.base.model.PageParams;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.mapper.UserMapper;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public IPage queryUserList(PageParams pageParams,@RequestBody(required = false) QueryUserParamsDto queryUserParamsDto) {
        Page page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper();
        return userMapper.selectPage(page, lqw);
    }
}
