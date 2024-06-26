package com.ecommerce_parent.user_service.services;

import com.ecommerce_parent.user_service.model.UserCredentialsInfo;

public interface UserRegistrationService {
    void registerUser(UserCredentialsInfo userCredentialsInfo);
    String loginUser(UserCredentialsInfo userCredentialsInfo);
}
