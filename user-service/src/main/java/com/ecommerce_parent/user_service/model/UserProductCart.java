package com.ecommerce_parent.user_service.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("UserProductCart")
@Data
public class UserProductCart {
    @Indexed(unique = true)
    private String userId;
    private List<ProductCart> productCartList;

}
