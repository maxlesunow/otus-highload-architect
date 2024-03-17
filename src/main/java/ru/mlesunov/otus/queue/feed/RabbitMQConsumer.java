package ru.mlesunov.otus.queue.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.service.FeedService;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumer {

    private final FeedService feedService;

    @RabbitListener(queues = {"${rabbitmq.feed.queue.name}"})
    public void consume(String postId){

        log.info("Message arrived! Message: " + postId);

        feedService.addNewPostToFeedByPostId(postId);
    }
}
