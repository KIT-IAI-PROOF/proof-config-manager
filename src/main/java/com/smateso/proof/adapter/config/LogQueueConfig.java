package com.smateso.proof.adapter.config;

import jakarta.annotation.PreDestroy;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class LogQueueConfig {

    @Value("${app.name}")
    private String appName;

    @Bean
    public Queue durableLogQueue() {
        return QueueBuilder.durable("logs." + appName).build();
    }

    @Bean
    public Binding durableLogBinding(final Queue durableLogQueue) {
        return BindingBuilder
            .bind(durableLogQueue)
            .to(new TopicExchange("logs"))
            .with("logs." + appName);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @PreDestroy
    public void deleteQueue(final RabbitAdmin rabbitAdmin) {
        rabbitAdmin.deleteQueue("logs." + appName);
    }

}
