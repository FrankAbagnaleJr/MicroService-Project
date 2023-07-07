package com.kyrie.user.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void testString(){
        redisTemplate.opsForValue().set("name","冀金梁");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name: " +name);
    }
}
