package com.basnetpr.product.service;

import com.basnetpr.product.dto.CategoryRequest;
import com.basnetpr.product.entity.Category;
import com.basnetpr.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category createCategory(CategoryRequest categoryRequest) {
        log.info("Inside createCategory of CategoryService");
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .categoryDescription(categoryRequest.getCategoryDescription())
                .build();
        return categoryRepository.save(category);
    }
    public Category getCategoryById(Long categoryId) {
        log.info("Inside getCategoryById of CategoryService");
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(Long id, CategoryRequest categoryRequest) {
        log.info("Inside updateCategory of CategoryService");
        Category category1 = getCategoryById(id);
        category1.setCategoryName(categoryRequest.getCategoryName());
        category1.setCategoryDescription(categoryRequest.getCategoryDescription());
        return categoryRepository.save(category1);
    }

    public void deleteCategory(Long id) {
        log.info("Inside deleteCategory of CategoryService");
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
