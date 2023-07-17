package com.kyrie.order.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听队列
 */
@Component
public class RabbitMQListener {

    //监听指定队列
    @RabbitListener(queues = "userQueue")
    public void ListenerQueue(Message message){
        byte[] body = message.getBody();
        String msg = new String(body);
        System.out.println(msg);
    }
}
