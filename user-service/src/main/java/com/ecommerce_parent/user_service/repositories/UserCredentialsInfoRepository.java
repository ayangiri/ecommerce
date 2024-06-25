package com.ecommerce_parent.user_service.repositories;

import com.ecommerce_parent.user_service.model.UserCredentialsInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCredentialsInfoRepository extends MongoRepository<UserCredentialsInfo,String> {
    Optional<UserCredentialsInfo> findByEmail(String email);
}
