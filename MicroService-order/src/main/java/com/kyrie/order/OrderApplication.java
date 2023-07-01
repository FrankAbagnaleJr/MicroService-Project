package com.kyrie.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther: jijin
 * @date: 2023/7/1 23:44 周六
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@EnableFeignClients
@MapperScan("com.kyrie.order.mapper")
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
