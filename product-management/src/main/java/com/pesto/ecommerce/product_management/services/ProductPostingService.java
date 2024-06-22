package com.pesto.ecommerce.product_management.services;

import com.pesto.ecommerce.product_management.model.Products;

public interface ProductPostingService {
    void saveProductInfo(Products product);

    void editProductInfo(Products product, String productId);
}
