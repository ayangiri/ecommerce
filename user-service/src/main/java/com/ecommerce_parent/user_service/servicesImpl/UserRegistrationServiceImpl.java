package com.ecommerce_parent.user_service.servicesImpl;

import com.ecommerce_parent.user_service.model.User;
import com.ecommerce_parent.user_service.model.UserCredentialsInfo;
import com.ecommerce_parent.user_service.repositories.UserCredentialsInfoRepository;
import com.ecommerce_parent.user_service.repositories.UserRepository;
import com.ecommerce_parent.user_service.services.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final UserCredentialsInfoRepository userCredentialsInfoRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public void registerUser(UserCredentialsInfo userCredentialsInfo) {
        Optional<User> userExists = userRepository.findByEmail(userCredentialsInfo.getEmail());
        if (userExists.isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }
        String encodedPassword = passwordEncoder.encode(userCredentialsInfo.getPassword());
        userCredentialsInfo.setPassword(encodedPassword);
        userCredentialsInfoRepository.save(userCredentialsInfo);
    }

    @Override
    public String loginUser(UserCredentialsInfo userCredentialsInfo) {
        Optional<UserCredentialsInfo> userExists = userCredentialsInfoRepository.findByEmail(userCredentialsInfo.getEmail());
        if (userExists.isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }
        String encodedPassword = passwordEncoder.encode(userCredentialsInfo.getPassword());
        if(userExists.get().getPassword().equalsIgnoreCase(encodedPassword)){
            String token = jwtService.generateToken(userCredentialsInfo);
        }
        return null;
    }
}
