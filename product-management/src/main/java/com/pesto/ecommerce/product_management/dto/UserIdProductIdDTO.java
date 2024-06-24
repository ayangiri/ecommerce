package com.pesto.ecommerce.product_management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserIdProductIdDTO {
    private String userId;
    private String productId;
}
