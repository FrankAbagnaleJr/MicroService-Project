package com.kyrie.user.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String USER_EXCHANGE_NAME = "user_exchange";
    private static final String USER_QUEUE_NAME = "user_queue";

    //定义交换机
    @Bean("userExchange")
    public Exchange UserExchange(){
        return ExchangeBuilder.topicExchange(USER_EXCHANGE_NAME).durable(true).build();
    }

    //定义队列
    @Bean("userQueue")
    public Queue userQueue() {
        return QueueBuilder.durable(USER_QUEUE_NAME).build();
    }

    //绑定交换机和队列
    @Bean
    public Binding bindExchangeQueue(@Qualifier("userExchange") Exchange exchange,@Qualifier("userQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("user.#").noargs();
    }

}
