package com.ecommerce_parent.user_service.repositories;

import com.ecommerce_parent.user_service.model.ProductCart;

public interface CustomUserCartRepository {
     void addProductToUsersCart(String userId, ProductCart productCart);
}
