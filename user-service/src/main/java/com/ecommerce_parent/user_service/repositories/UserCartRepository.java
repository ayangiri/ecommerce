package com.ecommerce_parent.user_service.repositories;

import com.ecommerce_parent.user_service.model.ProductCart;
import com.ecommerce_parent.user_service.model.UserProductCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends MongoRepository<UserProductCart,String>, CustomUserCartRepository {
    @RequiredArgsConstructor
    @Slf4j
    class CustomUserCartRepositoryImp implements CustomUserCartRepository {
        private final MongoTemplate mongoTemplate;

        @Override
        public void addProductToUsersCart(String userId, ProductCart productCart) {
            Query query = new Query(Criteria.where("_id").is(userId));
            Update update = new Update().push("productCartList", productCart);
            mongoTemplate.updateFirst(query, update, UserProductCart.class);
        }
    }
}
