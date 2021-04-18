package com.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Exchange getExchange(){
        return new TopicExchange("Topic_Exchange");
    }
    @Bean
    public Queue getQueue(){
        return new Queue("shipments_notifier");
    }
    @Bean
    public Binding bindQueueWithExchange(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("shipment");
    }

    @Bean
    public MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getMessageConverter());
        return rabbitTemplate;
    }
}
