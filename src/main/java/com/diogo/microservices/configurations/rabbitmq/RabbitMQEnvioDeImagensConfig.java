package com.diogo.microservices.configurations.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQEnvioDeImagensConfig {

    public static final String QUEUE_NAME = "imagemQueue";
    public static final String EXCHANGE_NAME = "imagem.process";
    public static final String ROUTING_KEY = "imagem.nova";


    @Bean
    public Queue imagemQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange imagemExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding imagemBinding(Queue imagemQueue, TopicExchange imagemExchange) {
        return BindingBuilder.bind(imagemQueue).to(imagemExchange).with(ROUTING_KEY);
    }
}
