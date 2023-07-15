package com.kyrie.user.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @auther: jijin
 * @date: 2023/7/2 18:24 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
@ToString
public class QueryUserParamsDto {
    private String name;
    private Integer age;
    private String create;
}
