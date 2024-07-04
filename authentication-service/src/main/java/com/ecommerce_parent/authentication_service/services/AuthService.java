package com.ecommerce_parent.authentication_service.services;

import com.ecommerce_parent.authentication_service.model.AuthorizationRequest;
import com.ecommerce_parent.authentication_service.model.AuthorizationResponse;
import com.ecommerce_parent.authentication_service.model.TokenClaims;
import com.ecommerce_parent.authentication_service.model.User;
import io.jsonwebtoken.Claims;

import java.security.InvalidKeyException;

public interface AuthService {
    AuthorizationResponse generateToken(User user);

    TokenClaims authenticateUser(AuthorizationRequest authorizationRequest) throws InvalidKeyException;
}
