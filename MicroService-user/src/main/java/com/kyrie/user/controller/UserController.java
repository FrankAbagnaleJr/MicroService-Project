package com.kyrie.user.controller;

import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.base.model.RestResponse;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.IUserService;
import com.kyrie.user.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:41 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@RestController
@Api(value = "用户管理接口", tags = "用户管理接口tags")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    IUserService iUserService;

    @ApiOperation("根据用户id查用户")
    @PostMapping("/list")
    public PageResult<User> list(PageParams pageParams, @RequestBody(required = false) QueryUserParamsDto queryUserParamsDto) {
        return userService.queryUserList(pageParams, queryUserParamsDto);
    }

//    @ApiOperation("查单个用户")
//    @GetMapping("/{id}")
//    public User getById(@PathVariable("id") Long id) {
//        return userService.selectById(id);
//    }

    @PostMapping
    public RestResponse saveUser(@RequestBody User user) {
        return new RestResponse<>(iUserService.save(user),null);
    }


    @PutMapping
    public RestResponse updataUser(@RequestBody User user) {
        return new RestResponse<>(iUserService.updateById(user),null);
    }

    @DeleteMapping("{id}")
    public RestResponse deleteUser(@PathVariable Long id) {
        return new RestResponse<>(iUserService.removeById(id),null);
    }

    @GetMapping("{id}")
    public User getBuId(@PathVariable Long id) {
        return iUserService.getById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return iUserService.list();
    }


}
