package com.kyrie.base.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long PageNum;

    //每页记录数
    private Long PageSize;
}
