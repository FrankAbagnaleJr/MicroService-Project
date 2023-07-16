package com.kyrie.user.service.impl;

import com.kyrie.user.pojo.User;
import com.mysql.jdbc.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

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

    @Test
    void teststr() {
        User user = null;
        System.out.println("输出空对象：" + user);
        if (Objects.nonNull(user)) {
            System.out.println("user不为空");
        } else {
            System.out.println("user为空");
        }

        String s = String.valueOf(user);
        System.out.println("输出空对象转化成字符串：" + s.toString());
        if (StringUtils.isNullOrEmpty(s)) {
            System.out.println("转化后的user为空");
        } else {
            System.out.println("转化后的user不为空");
        }

        System.out.println("null");
    }
}
