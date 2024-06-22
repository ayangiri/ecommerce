package com.pesto.ecommerce.product_management.resource;

import com.pesto.ecommerce.product_management.model.ProductSummary;
import com.pesto.ecommerce.product_management.model.Products;
import com.pesto.ecommerce.product_management.services.ProductListingService;
import com.pesto.ecommerce.product_management.services.ProductPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductResource {
    private final ProductListingService productListingService;
    private final ProductPostingService productPostingService;

    @GetMapping(value = "/listing", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProductListing() {
        List<ProductSummary> allProductsDetails = productListingService.getAllProductsDetails();
        return ResponseEntity.ok(allProductsDetails);
    }

    @GetMapping(value = "/details/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductDetailsById(@PathVariable(value = "productId") String productId) throws NoSuchFieldException {
        Products productDetailsById = productListingService.getProductDetailsById(productId);
        return ResponseEntity.ok(productDetailsById);
    }

    @PostMapping(value = "/save/info", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveNewProductDetails(@RequestBody Products products) {
        productPostingService.saveProductInfo(products);
        return ResponseEntity.ok("Success");
    }

    @PutMapping(value = "/edit/info/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editProductInfoOfExistingProduct(@RequestBody Products products,
                                                              @PathVariable(value = "productId") String productId) {
        productPostingService.editProductInfo(products, productId);
        return ResponseEntity.ok("Success");
    }
}
