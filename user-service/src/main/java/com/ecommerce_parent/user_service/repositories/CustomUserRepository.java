package com.ecommerce_parent.user_service.repositories;

import com.ecommerce_parent.user_service.model.User;

public interface CustomUserRepository {
    User findByEmail(String email);
}
