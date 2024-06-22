package com.pesto.ecommerce.product_management.repository;

import com.pesto.ecommerce.product_management.model.Products;

public interface CustomProductRepository {
    void updateProductDetails(Products product, String productId);
}
