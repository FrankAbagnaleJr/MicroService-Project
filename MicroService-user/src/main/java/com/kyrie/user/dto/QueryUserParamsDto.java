package com.kyrie.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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

    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

}
