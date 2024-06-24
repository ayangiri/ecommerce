package com.pesto.ecommerce.product_management.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductCartService {
    void saveProductToUserCart(String userId, String productId) throws JsonProcessingException;
}
