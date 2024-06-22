package com.pesto.ecommerce.product_management.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;

@Data
@Document("products")
public class Products {
    @Id
    private String id;
    private String name;
    private String amount;
    private String category;
    private List<String> productImagesUrl;
    private String productThumbNail;
    private int rating;
    private String shortDescription;
    private int inventoryCount;
    private int discount;
    private String description;
    private String vendor;
}
