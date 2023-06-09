package com.digdes.java2023.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend(exchange, routingKey, "New message");
    }
}
