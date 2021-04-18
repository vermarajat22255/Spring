package com.rabbitmq.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OrderStatus {
    private String status;
    private String message;
    private Order order;
}
