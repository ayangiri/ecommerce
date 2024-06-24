package com.pesto.ecommerce.product_management.servicesImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pesto.ecommerce.product_management.config.KafkaProducerConfig;
import com.pesto.ecommerce.product_management.dto.UserIdProductIdDTO;
import com.pesto.ecommerce.product_management.services.ProductCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {
    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topics.productCartAddition}")
    private String productCartTopic;

    @Override
    public void saveProductToUserCart(String userId, String productId) throws JsonProcessingException {
        UserIdProductIdDTO userIdProductIdDTO = UserIdProductIdDTO.builder()
                .userId(userId).productId(productId)
                .build();
        String message = objectMapper.writeValueAsString(userIdProductIdDTO);
        kafkaProducerService.putKafkaMessage(message, productCartTopic);
    }
}
