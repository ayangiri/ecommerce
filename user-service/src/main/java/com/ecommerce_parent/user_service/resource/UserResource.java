package com.ecommerce_parent.user_service.resource;

import com.ecommerce_parent.user_service.model.UserCredentialsInfo;
import com.ecommerce_parent.user_service.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity registerUser(@RequestBody UserCredentialsInfo userCredentialsInfo) {
        userRegistrationService.registerUser(userCredentialsInfo);
        return ResponseEntity.ok("Success");
    }

//    @PostMapping("/login")
//    public

}
