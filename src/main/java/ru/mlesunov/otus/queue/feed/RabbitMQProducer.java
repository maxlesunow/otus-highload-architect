package ru.mlesunov.otus.queue.feed;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    @Value("${rabbitmq.feed.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.feed.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
