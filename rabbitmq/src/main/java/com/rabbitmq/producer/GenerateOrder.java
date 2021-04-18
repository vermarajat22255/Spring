package com.rabbitmq.producer;

import com.rabbitmq.model.Order;
import com.rabbitmq.model.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GenerateOrder {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/order/{restaurantName}")
    public String order(@RequestBody Order orderDetails, @PathVariable String restaurantName){
        orderDetails.setOrderId(UUID.randomUUID().toString());
        OrderStatus status = new OrderStatus("In process","Order placed at "+restaurantName, orderDetails);
        rabbitTemplate.convertAndSend("Topic_Exchange","shipment", status);
        return "Success";
    }
}
