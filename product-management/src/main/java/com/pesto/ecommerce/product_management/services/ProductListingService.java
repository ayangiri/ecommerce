package com.pesto.ecommerce.product_management.services;

import com.pesto.ecommerce.product_management.model.ProductSummary;
import com.pesto.ecommerce.product_management.model.Products;

import java.util.List;

public interface ProductListingService {
    List<ProductSummary> getAllProductsDetails();

    Products getProductDetailsById(String id) throws NoSuchFieldException;
}
