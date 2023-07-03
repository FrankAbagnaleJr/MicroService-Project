package com.kyrie.order.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: jijin
 * @date: 2023/7/2 22:00 周日
 * @project_name: MicroService-Project
 * @version: 1.0
 * @description TODO
 */
@MapperScan("com.kyrie.order.mapper")
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //创建一个拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //创建一个分页拦截器
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        //给拦截器添加分页拦截器
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        //返回拦截器
        return interceptor;
    }
}
