package com.tuanmhoang.kafka.springboot.simple.service.impl;

import com.tuanmhoang.kafka.springboot.simple.service.KafkaProducerService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatchMessagesKafkaProducerService implements KafkaProducerService {

    @Value(value = "${spring.kafka.topic.batch}")
    private String topicName;

    private final KafkaTemplate<String, String> batchMessagesKafkaTemplate;

    @Override
    public void sendMessage(String baseMessage) {
        List<String> messages = buildMessagesForBatch(baseMessage);
        messages.forEach( message -> {
            CompletableFuture<SendResult<String, String>> future = batchMessagesKafkaTemplate.send(topicName, message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message=[{}] with offset=[{}]", message,result.getRecordMetadata().offset());
                } else {
                    log.info("Unable to send message=[{}] due to: {}", message, ex.getMessage());
                }
            });
        });
    }

    private List<String> buildMessagesForBatch(String baseMessage) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messages.add(baseMessage + StringUtils.SPACE + i);
        }
        return messages;
    }
}
