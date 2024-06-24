package com.pesto.ecommerce.product_management.servicesImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pesto.ecommerce.product_management.config.KafkaProducerCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class KafkaProducerService {
    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Autowired
    private ObjectMapper objectMapper;

    public void putKafkaMessage(String message , String topic) {
        try {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, message);
            kafkaProducer.send(producerRecord, new KafkaProducerCallback(message, topic));
        } catch (Exception ex) {
            log.info("Error in json processing {}", message);
        }
    }
}
