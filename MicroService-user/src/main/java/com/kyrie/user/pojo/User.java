package com.kyrie.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:38 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
@ToString
@TableName("tb_user")
public class User {
    @TableId(value = "id",type = IdType.AUTO)
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

