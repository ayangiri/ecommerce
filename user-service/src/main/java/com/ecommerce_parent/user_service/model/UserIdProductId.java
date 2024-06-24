package com.ecommerce_parent.user_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserIdProductId {
    private String userId;
    private String productId;
}
