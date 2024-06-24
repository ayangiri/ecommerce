package com.ecommerce_parent.user_service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProductCart {
    private String productId;
    private Date addedDate;
}
