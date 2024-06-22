package com.pesto.ecommerce.product_management.servicesImpl;

import com.pesto.ecommerce.product_management.model.Products;
import com.pesto.ecommerce.product_management.repository.ProductRepository;
import com.pesto.ecommerce.product_management.services.ProductPostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductPostingServiceImpl implements ProductPostingService {
    private final ProductRepository productRepository;

    @Override
    public void saveProductInfo(Products product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error while saving product info {}", product.getName(), e);
            throw new RuntimeException("Error while saving product");
        }
    }

    @Override
    public void editProductInfo(Products product, String productId) {
        productRepository.updateProductDetails(product, productId);
    }
}
