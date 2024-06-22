package com.pesto.ecommerce.product_management.repository;

import com.pesto.ecommerce.product_management.model.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Field;
import java.util.Objects;

public interface ProductRepository extends MongoRepository<Products, String>, CustomProductRepository {
    @RequiredArgsConstructor
    @Slf4j
    class ProductRepositoryImpl implements CustomProductRepository {
        private final MongoTemplate mongoTemplate;

        @Override
        public void updateProductDetails(Products product, String productId) {
            ObjectId objectId = new ObjectId(productId);
            Query query = new Query(Criteria.where("id").is(objectId));
            Products existingProduct = mongoTemplate.findOne(query, Products.class);
            if (Objects.isNull(existingProduct)) {
                throw new RuntimeException("Product not found");
            }
            for (Field field : Products.class.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object newValue = field.get(product);
                    if (newValue != null) {
                        field.set(existingProduct, newValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            // Save the updated entity
            mongoTemplate.save(existingProduct);

        }
    }

}
