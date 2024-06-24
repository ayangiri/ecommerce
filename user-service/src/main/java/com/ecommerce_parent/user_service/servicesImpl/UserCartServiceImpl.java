package com.ecommerce_parent.user_service.servicesImpl;

import com.ecommerce_parent.user_service.model.ProductCart;
import com.ecommerce_parent.user_service.model.UserIdProductId;
import com.ecommerce_parent.user_service.model.UserProductCart;
import com.ecommerce_parent.user_service.repositories.UserCartRepository;
import com.ecommerce_parent.user_service.services.UserCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCartServiceImpl implements UserCartService {
    private final UserCartRepository userCartRepository;

    @Override
    public void addProductToUserCart(UserIdProductId userIdProductId) {
        ProductCart productCart = ProductCart.builder()
                .productId(userIdProductId.getProductId()).addedDate(new Date())
                .build();
        userCartRepository.addProductToUsersCart(userIdProductId.getUserId(), productCart);
    }
}
