package com.kyrie.base.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @auther: jijin
 * @date: 2023/7/2 17:52 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {

    //页码
    @NotEmpty
    @Min(1)
    private Long pageNum;

    //每页记录数
    @NotEmpty
    @Min(1)
    private Long pageSize;
}
