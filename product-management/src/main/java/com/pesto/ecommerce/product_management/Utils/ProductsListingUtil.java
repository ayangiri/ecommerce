package com.pesto.ecommerce.product_management.Utils;

import com.pesto.ecommerce.product_management.model.ProductSummary;
import com.pesto.ecommerce.product_management.model.Products;

public class ProductsListingUtil {
    public static ProductSummary getProductSummaryDto(Products products) {
        ProductSummary productSummary = ProductSummary.builder().productId(products.getId())
                .productThumbNail(products.getProductThumbNail()).name(products.getName())
                .shortDescription(products.getShortDescription())
                .category(products.getCategory())
                .discount(products.getDiscount()).rating(products.getRating())
                .build();
        return productSummary;
    }
}
