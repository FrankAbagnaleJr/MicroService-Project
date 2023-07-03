package com.kyrie.order.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @auther: jijin
 * @date: 2023/7/1 23:59 周六
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@Data
@ToString
@TableName("tb_order")
public class Order {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long price;
    private String name;
    private Integer sort;
    @TableField("userid")
    private Long userId;
    @TableField(exist = false)
    private User user;
}
