package com.ecommerce_parent.user_service.resource;

import com.ecommerce_parent.user_service.model.UserCredentialsInfo;
import com.ecommerce_parent.user_service.services.UserRegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserRegistrationService userRegistrationService;

    @PostMapping("/signUp")
    public ResponseEntity registerUser(@RequestBody UserCredentialsInfo userCredentialsInfo) throws JsonProcessingException {
        String token = userRegistrationService.registerUser(userCredentialsInfo);
        if(StringUtils.hasText(token)){
            record res(String token){};
            return ResponseEntity.ok().body(new res(token));
        } return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserCredentialsInfo userCredentialsInfo) throws JsonProcessingException {
        String token = userRegistrationService.loginUser(userCredentialsInfo);
        if(StringUtils.hasText(token)){
            record res(String token){};
            return ResponseEntity.ok().body(new res(token));
        } return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }




}
