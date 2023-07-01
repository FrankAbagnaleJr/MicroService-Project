package com.kyrie.order.pojo;

import lombok.Data;

/**
 * @auther: jijin
 * @date: 2023/7/1 23:59 周六
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@Data
public class Order {

    private Long id;
    private Long price;
    private String name;
    private Integer sort;
    private Long userId;
    private User user;
}
