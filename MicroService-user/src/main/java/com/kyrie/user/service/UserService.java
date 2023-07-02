package com.kyrie.user.service;

import com.kyrie.base.model.PageParams;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryUserList(PageParams page,QueryUserParamsDto dto);
}
