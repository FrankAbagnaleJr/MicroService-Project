package com.kyrie.user.test;

import com.kyrie.user.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class RabbitMQSendMsg {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sentMsg(){
        rabbitTemplate.convertAndSend("user_exchange","user.order","用户生成了订单："+ UUID.randomUUID().toString());
    }

}
