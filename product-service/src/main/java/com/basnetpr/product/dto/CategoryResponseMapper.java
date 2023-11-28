package com.basnetpr.product.dto;

import com.basnetpr.product.entity.Category;
import io.micrometer.common.KeyValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryResponseMapper implements Function<Category, CategoryResponse> {

    private final ProductResponseMapper productResponseMapper;

    @Override
    public CategoryResponse apply(Category category) {
        log.info("Mapping category to category response");
        List<ProductResponse> products = category.getProducts() != null ? category.getProducts().stream().map(productResponseMapper).toList() : new ArrayList<>();
        return new CategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription(),
                products
        );
    }
}
