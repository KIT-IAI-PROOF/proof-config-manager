package com.smateso.proof.adapter.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogQueueConfig {

    @Value("${app.name}")
    private String appName;

    @Bean
    public Queue durableLogQueue() {
        return QueueBuilder.durable("logs." + appName).build();
    }

    @Bean
    public Binding durableLogBinding(Queue durableLogQueue) {
        return BindingBuilder
            .bind(durableLogQueue)
            .to(new TopicExchange("logs"))
            .with("logs." + appName);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
