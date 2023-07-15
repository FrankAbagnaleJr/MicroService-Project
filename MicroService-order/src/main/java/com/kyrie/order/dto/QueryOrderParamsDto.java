package com.kyrie.order.dto;

import com.kyrie.base.model.PageParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @auther: jijin
 * @date: 2023/7/2 23:08 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
@ToString
@ApiModel(description = "分页条件模型类")
public class QueryOrderParamsDto {

    @Size()
    private String name;

    @Min(0)
    private double price;

    @Min(0)
    private Long number;
}
