package com.kyrie.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @auther: jijin
 * @date: 2023/7/2 17:58 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    //返回的数据列表
    private List<T> items;
    //总记录输
    private Long counts;
    //当前页码
    private Long pageNum;
    //每页记录数
    private Long pageSize;

}
