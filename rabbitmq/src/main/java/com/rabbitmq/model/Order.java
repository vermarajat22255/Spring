package com.rabbitmq.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private String orderId;
    private String name;
    private int price;
    private int qty;
}
