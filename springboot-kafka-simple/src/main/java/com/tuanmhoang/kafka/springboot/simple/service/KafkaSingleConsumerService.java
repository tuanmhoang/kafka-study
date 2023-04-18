package com.tuanmhoang.kafka.springboot.simple.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSingleConsumerService {

    @KafkaListener(
        topics = "${spring.kafka.topic.single}",
        groupId = "${spring.kafka.group-id}"
    )
    public void listenSingleTopic(String message) {
        log.info("Received Message in group foo: {}",message);
    }
}
