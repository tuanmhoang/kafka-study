package com.tuanmhoang.kafka.springboot.simple.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.linger}")
    private String linger;

    @Value(value = "${spring.kafka.batchSize}")
    private String batchSize;

    @Bean
    public Map<String, Object> basePropsConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootstrapAddress);
        configProps.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
        configProps.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
        return configProps;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(basePropsConfigs());
    }

    @Bean(name = "singleMessageKafkaTemplate")
    public KafkaTemplate<String, String> singleMessageKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Map<String, Object> batchPropsConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootstrapAddress);
        configProps.put(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);
        configProps.put(
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            StringSerializer.class);

        // for batch configs
        configProps.put(
            ProducerConfig.LINGER_MS_CONFIG,
            linger);
        configProps.put(
            ProducerConfig.BATCH_SIZE_CONFIG,
            batchSize);
        configProps.put(
            ProducerConfig.COMPRESSION_TYPE_CONFIG,
            "snappy");
        return configProps;
    }

    @Bean(name = "batchMessagesKafkaTemplate")
    public KafkaTemplate<String, String> batchMessagesKafkaTemplate() {
        ProducerFactory<String, String> producerFactory =
            new DefaultKafkaProducerFactory<>(batchPropsConfigs());
        return new KafkaTemplate<>(producerFactory);
    }
}