package com.ecommerce_parent.authentication_service.controller;

import com.ecommerce_parent.authentication_service.model.AuthorizationRequest;
import com.ecommerce_parent.authentication_service.model.AuthorizationResponse;
import com.ecommerce_parent.authentication_service.model.TokenClaims;
import com.ecommerce_parent.authentication_service.model.User;
import com.ecommerce_parent.authentication_service.services.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/generate/token")
    public ResponseEntity<AuthorizationResponse> generateToken(@RequestBody User user){
        AuthorizationResponse authorizationResponse = authService.generateToken(user);
        return ResponseEntity.ok(authorizationResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthorizationRequest authorizationRequest) throws InvalidKeyException {
        TokenClaims validToken = authService.authenticateUser(authorizationRequest);
        return ResponseEntity.ok(validToken);
    }
}
