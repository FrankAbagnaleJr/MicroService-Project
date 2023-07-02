package com.kyrie.user.controller;

import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:41 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@RestController
@Api(value = "用户管理接口",tags = "用户管理接口tags")
public class UserContraller {

    @Autowired
    UserService userService;

    @ApiOperation("根据用户id查用户")
    @GetMapping("/list")
    public PageResult<User> list(PageParams pageParams,@RequestBody(required = false) QueryUserParamsDto queryUserParamsDto) {
        return userService.queryUserList(pageParams, queryUserParamsDto);
    }
}
