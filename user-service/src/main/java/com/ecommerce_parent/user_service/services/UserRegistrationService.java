package com.ecommerce_parent.user_service.services;

import com.ecommerce_parent.user_service.model.UserCredentialsInfo;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserRegistrationService {
    String registerUser(UserCredentialsInfo userCredentialsInfo) throws JsonProcessingException;
    String loginUser(UserCredentialsInfo userCredentialsInfo) throws JsonProcessingException;
}
