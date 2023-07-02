package com.kyrie.user;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auther: jijin
 * @date: 2023/7/1 23:45 周六
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@SpringBootApplication
@EnableSwagger2
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
