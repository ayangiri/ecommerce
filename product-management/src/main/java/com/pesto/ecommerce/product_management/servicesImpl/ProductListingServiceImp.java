package com.pesto.ecommerce.product_management.servicesImpl;

import com.pesto.ecommerce.product_management.Utils.ProductsListingUtil;
import com.pesto.ecommerce.product_management.model.ProductSummary;
import com.pesto.ecommerce.product_management.model.Products;
import com.pesto.ecommerce.product_management.repository.ProductRepository;
import com.pesto.ecommerce.product_management.services.ProductListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductListingServiceImp implements ProductListingService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductSummary> getAllProductsDetails() {
        List<Products> allProducts = productRepository.findAll();
        List<ProductSummary> productSummaryOfAllProducts = allProducts.stream().map(ProductsListingUtil::getProductSummaryDto).collect(Collectors.toList());
        return productSummaryOfAllProducts;
    }

    @Override
    public Products getProductDetailsById(String id) throws NoSuchFieldException {
        Optional<Products> productById = productRepository.findById(id);
        if (productById.isPresent()) {
            return productById.get();
        }
        throw new NoSuchFieldException("No Such Product Exist");
    }
}
