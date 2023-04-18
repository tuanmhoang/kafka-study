package com.tuanmhoang.kafka.springboot.simple.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaBatchConsumerService {
    @KafkaListener(
        topics = "${spring.kafka.topic.batch}",
        groupId = "${spring.kafka.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenBatchTopic(@Payload List<String> messages,
        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("Beginning to consume batch messages");
        for (int i = 0; i < messages.size(); i++) {
            log.info("Received message=[{}] with partition=[{}] - offset=[{}]",
                messages.get(i), partitions.get(i), offsets.get(i));
        }
        log.info("Batch messages are consumed");
    }
}
