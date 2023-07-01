package com.kyrie.user.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:38 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
public class User {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String profession;
    private Integer age;
    private Integer gender;
    private Integer status;
    private Date createtime;

}

