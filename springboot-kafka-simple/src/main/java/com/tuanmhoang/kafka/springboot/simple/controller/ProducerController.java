package com.tuanmhoang.kafka.springboot.simple.controller;

import com.tuanmhoang.kafka.springboot.simple.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final KafkaProducerService singleMessageKafkaProducerService;

    private final KafkaProducerService batchMessagesKafkaProducerService;

    public ProducerController(
        @Qualifier("singleMessageKafkaProducerService") KafkaProducerService singleMessageKafkaProducerService,
        @Qualifier("batchMessagesKafkaProducerService") KafkaProducerService batchMessagesKafkaProducerService
    ) {
        this.singleMessageKafkaProducerService = singleMessageKafkaProducerService;
        this.batchMessagesKafkaProducerService = batchMessagesKafkaProducerService;
    }

    @GetMapping("/single/{msg}")
    public ResponseEntity<String> produceSingleMessage(@PathVariable String msg) {
        singleMessageKafkaProducerService.sendMessage(msg);
        return ResponseEntity.ok("done single");
    }

    @GetMapping("/batch/{baseMsg}")
    public ResponseEntity<String> produceBatchMessages(@PathVariable String baseMsg) {
        batchMessagesKafkaProducerService.sendMessage(baseMsg);
        return ResponseEntity.ok("done batch");
    }
}
