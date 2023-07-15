package com.kyrie.order.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
@ApiModel(value = "订单类",description = "订单实体类")
public class Order {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @NotEmpty
    @Min(0)
    private Long price;

    @Size(min = 1)
    private String name;

    @Min(0)
    private Integer sort;

    @NotEmpty
    @TableField("userid")
    private Long userId;


    @TableField(exist = false)
    private User user;
}
