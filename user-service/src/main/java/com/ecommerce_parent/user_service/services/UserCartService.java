package com.ecommerce_parent.user_service.services;

import com.ecommerce_parent.user_service.model.UserIdProductId;

public interface UserCartService {
    void addProductToUserCart(UserIdProductId userIdProductId);
}
