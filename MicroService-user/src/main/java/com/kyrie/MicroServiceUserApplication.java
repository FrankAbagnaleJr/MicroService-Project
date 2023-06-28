package com.kyrie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kyrie.mapper")
@SpringBootApplication
public class MicroServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceUserApplication.class, args);
    }

}
