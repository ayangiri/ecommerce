package com.ecommerce_parent.user_service.servicesImpl;

import com.ecommerce_parent.user_service.model.AuthorizationResponse;
import com.ecommerce_parent.user_service.model.User;
import com.ecommerce_parent.user_service.model.UserCredentialsInfo;
import com.ecommerce_parent.user_service.repositories.UserCredentialsInfoRepository;
import com.ecommerce_parent.user_service.repositories.UserRepository;
import com.ecommerce_parent.user_service.services.UserRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final UserCredentialsInfoRepository userCredentialsInfoRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Override
    public String registerUser(UserCredentialsInfo userCredentialsInfo) throws JsonProcessingException {
        Optional<User> userExists = userRepository.findByEmail(userCredentialsInfo.getEmail());
        if (userExists.isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }
        String encodedPassword = passwordEncoder.encode(userCredentialsInfo.getPassword());
        userCredentialsInfo.setPassword(encodedPassword);
        userCredentialsInfoRepository.save(userCredentialsInfo);
        String token = generateAuthToken(userCredentialsInfo);
        return token;

    }

    @Override
    public String loginUser(UserCredentialsInfo userCredentialsInfo) throws JsonProcessingException {
        Optional<UserCredentialsInfo> userExists = userCredentialsInfoRepository.findByEmail(userCredentialsInfo.getEmail());
        if (userExists.isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }
        String encodedPassword = passwordEncoder.encode(userCredentialsInfo.getPassword());
        if(userExists.get().getPassword().equalsIgnoreCase(encodedPassword)){
            String token = generateAuthToken(userCredentialsInfo);
            return token;
        }
        return null;
    }

    private String generateAuthToken(UserCredentialsInfo user) throws JsonProcessingException {
        String url = "localhost:9092/generate/token";
        HttpEntity<Object> httpEntity = new HttpEntity<>(user,null);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if(exchange.getStatusCode().equals(HttpStatus.OK)){
            AuthorizationResponse authorizationResponse = objectMapper.readValue(exchange.getBody(), AuthorizationResponse.class);
            return authorizationResponse.getToken();
        }
        return null;
    }
}
