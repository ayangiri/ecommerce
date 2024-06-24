package com.ecommerce_parent.user_service.consumers;

import com.ecommerce_parent.user_service.model.UserIdProductId;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCartAdditionConsumer {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topics.productCartAddition}")
    public void handleDavidModeratorEvents(String message, @Header(KafkaHeaders.OFFSET) Long offset,
                                           @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
                                           Acknowledgment acknowledgment) {
        try {
            UserIdProductId userIdProductId = objectMapper.convertValue(message, UserIdProductId.class);

        } catch (Exception e) {

        } finally {
            acknowledgment.acknowledge();
        }
    }
}
