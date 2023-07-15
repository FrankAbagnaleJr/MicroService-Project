package com.kyrie.user.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        //配置类
        Config config = new Config();

        //配置地址端口
//        config.useClusterServers().addNodeAddress("redis://192.168.101.65:6379").setPassword("redis");
        config.useSingleServer().setAddress("redis://192.168.101.65:6379").setPassword("redis");
        //把配置类给客户端，然后返回客户端
        return Redisson.create(config);
    }
}
