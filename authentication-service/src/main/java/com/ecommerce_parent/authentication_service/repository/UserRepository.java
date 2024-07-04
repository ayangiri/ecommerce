package com.ecommerce_parent.authentication_service.repository;

import com.ecommerce_parent.authentication_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}