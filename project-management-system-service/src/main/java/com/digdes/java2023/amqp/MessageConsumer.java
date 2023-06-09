package com.digdes.java2023.amqp;

import com.digdes.java2023.mail.TestMailSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.mail.MessagingException;

@Configuration
public class MessageConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final TestMailSender testMailSender;

    public MessageConsumer(RabbitTemplate rabbitTemplate, TestMailSender testMailSender) {
        this.rabbitTemplate = rabbitTemplate;
        this.testMailSender = testMailSender;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(Message message) throws MessagingException {
        testMailSender.send("lesson 8 test");
    }
}
