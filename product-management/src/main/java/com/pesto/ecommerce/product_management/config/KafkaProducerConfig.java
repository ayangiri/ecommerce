package com.pesto.ecommerce.product_management.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaProducer<String, String> kafkaProducer(@Value("${spring.kafka.bootstrap-servers}") String kafkaBootstrapServers) {
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", kafkaBootstrapServers);
        producerProperties.put("acks", "all");
        producerProperties.put("retries", 1);
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("request.timeout.ms", "1000");
        return new KafkaProducer<>(producerProperties);
    }
}
