package com.pesto.ecommerce.product_management.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class ProductSummary {
    private String productId;
    private String name;
    private String shortDescription;
    private String amount;
    private String category;
    private int discount;
    private String productThumbNail;
    private int rating;
}
