package com.rabbitmq.app;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(String message) throws BusinessException {
        System.out.println("Message " + message + "  " + LocalDateTime.now());
        
    }

}