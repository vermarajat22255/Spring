package com.rabbitmq.consumer;

import com.rabbitmq.model.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    @RabbitListener(queues = "shipments_notifier")
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println(orderStatus);
    }
}
