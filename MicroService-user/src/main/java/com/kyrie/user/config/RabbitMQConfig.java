package com.kyrie.user.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String USER_EXCHANGE_NAME = "userExchange";
    private final String USER_QUEUE_NAME = "userQueue";

    //定义交换机
    @Bean(USER_QUEUE_NAME)
    public Exchange UserExchange(){
        return ExchangeBuilder.topicExchange(USER_EXCHANGE_NAME).durable(true).build();
    }

    //定义队列
    @Bean(USER_QUEUE_NAME)
    public Queue userQueue() {
        return QueueBuilder.durable(USER_QUEUE_NAME).build();
    }

    //绑定交换机和队列
    public Binding bindExchangeQueue(@Qualifier(USER_EXCHANGE_NAME) Exchange exchange,@Qualifier(USER_QUEUE_NAME) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("user.#").noargs();
    }

}
