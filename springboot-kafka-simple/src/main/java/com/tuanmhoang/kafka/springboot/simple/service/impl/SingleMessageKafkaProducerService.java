package com.tuanmhoang.kafka.springboot.simple.service.impl;

import com.tuanmhoang.kafka.springboot.simple.service.KafkaProducerService;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SingleMessageKafkaProducerService implements KafkaProducerService {

    @Value(value = "${spring.kafka.topic.single}")
    private String topicName;

    private final KafkaTemplate<String, String> singleMessageKafkaTemplate;

    @Override
    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = singleMessageKafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", message,result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message=[{}] due to: {}", message, ex.getMessage()); //TODO unit test
            }
        });
    }
}
