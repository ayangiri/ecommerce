package com.ecommerce_parent.user_service.repositories;

import com.ecommerce_parent.user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

}
