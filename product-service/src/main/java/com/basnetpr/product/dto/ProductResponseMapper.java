package com.basnetpr.product.dto;

import com.basnetpr.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class ProductResponseMapper implements Function<Product, ProductResponse> {
    @Override
    public ProductResponse apply(Product product) {
        log.info("Inside apply of ProductResponseMapper");
        return new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductQuantity(),
                product.getProductCategory().getCategoryId()
        );
    }
}
