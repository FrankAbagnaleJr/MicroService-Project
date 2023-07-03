package com.kyrie.order;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auther: jijin
 * @date: 2023/7/1 23:44 周六
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
