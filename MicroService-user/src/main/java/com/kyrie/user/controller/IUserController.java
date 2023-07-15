package com.kyrie.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.RestResponse;
import com.kyrie.user.dto.QueryUserParamsDto;
import com.kyrie.user.pojo.User;
import com.kyrie.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:41 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@RestController
@Api(value = "用户管理接口", tags = "用户管理接口tags")
//@RequestMapping("/user")
public class IUserController {
    @Autowired
    IUserService iUserService;

    @ApiOperation("按条件查询返回分页数据")
    @PostMapping("/listpage")
    public RestResponse list(@Validated PageParams pageParams, @RequestBody(required = false) QueryUserParamsDto queryUserParamsDto) {
        return new RestResponse<>(true, null, iUserService.queryUserList(pageParams, queryUserParamsDto));
    }

//    @ApiOperation("根据id查单个用户")
//    @GetMapping("{id}")
//    public RestResponse getById(@PathVariable Long id) {
//        return new RestResponse(true,"",iUserService.getById(id));
//    }

    @ApiOperation("根据id查单个用户")
    @GetMapping("/{id}")
    public RestResponse qureyById(@PathVariable Long id) {
        return new RestResponse(true, "", iUserService.queryById(id));
    }

    @ApiOperation("根据id查单个用户")
    @GetMapping("/getById/{id}")
    public User getById(@PathVariable Long id) {
        return iUserService.queryById(id);
    }


    @ApiOperation("新增用户")
    @PostMapping
    public RestResponse saveUser(@RequestBody User user) {
        return new RestResponse<>(iUserService.save(user));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("{id}")
    public RestResponse deleteUser(@PathVariable Long id) {
        return new RestResponse<>(iUserService.removeById(id));
    }

    @ApiOperation("修改用户")
    @PutMapping
    public RestResponse updataUser(@RequestBody User user) {
        return new RestResponse<>(iUserService.updateById(user));
    }
}
