package com.tuanmhoang.kafka.springboot.simple.service;

public interface KafkaProducerService {

    void sendMessage(String message);
}
