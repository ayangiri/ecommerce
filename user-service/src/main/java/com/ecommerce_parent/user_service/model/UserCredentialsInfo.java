package com.ecommerce_parent.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Document("userCredentialsInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCredentialsInfo{
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;
}
