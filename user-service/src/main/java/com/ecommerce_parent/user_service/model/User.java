package com.ecommerce_parent.user_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("userDetails")
public class User {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private Long mobileNumber;
    private String address;
    @Indexed(unique = true)
    private String email;
}
