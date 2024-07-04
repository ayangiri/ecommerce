package com.ecommerce_parent.authentication_service.services;

import com.ecommerce_parent.authentication_service.model.AuthorizationRequest;
import com.ecommerce_parent.authentication_service.model.AuthorizationResponse;
import com.ecommerce_parent.authentication_service.model.TokenClaims;
import com.ecommerce_parent.authentication_service.model.User;
import com.ecommerce_parent.authentication_service.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public AuthorizationResponse generateToken(User user) {
        String token = jwtService.generateToken(user);
        return AuthorizationResponse.builder().token(token).build();
    }

    @Override
    public TokenClaims authenticateUser(AuthorizationRequest authorizationRequest) throws InvalidKeyException {
        Claims claims = jwtService.extractAllClaims(authorizationRequest.getToken());
        String subject = claims.getSubject();
        Optional<User> email = userRepository.findByEmail(subject);
        if (email.isPresent() && !jwtService.isTokenExpired(authorizationRequest.getToken())) {
            return TokenClaims.builder().email(claims.getSubject())
                    .expiration(claims.getExpiration()).build();
        }
        throw new InvalidKeyException("Invalid token");
    }


}
