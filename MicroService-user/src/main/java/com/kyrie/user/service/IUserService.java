package com.kyrie.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kyrie.base.model.PageParams;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.pojo.User;

public interface IUserService extends IService<User> {
    IPage queryUserList(PageParams pageParams, QueryUserParamsDto queryUserParamsDto);

    User queryById(Long id);

    boolean updateUserByID(User user);
}
