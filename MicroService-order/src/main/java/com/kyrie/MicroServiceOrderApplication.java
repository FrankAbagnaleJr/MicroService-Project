package com.kyrie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.kyrie.mapper")
@EnableFeignClients
@SpringBootApplication
public class MicroServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceOrderApplication.class, args);
    }

}
