package com.basnetpr.product.controller;

import com.basnetpr.product.dto.*;
import com.basnetpr.product.entity.Category;
import com.basnetpr.product.entity.Product;
import com.basnetpr.product.service.CategoryService;
import com.basnetpr.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductResponseMapper productResponseMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("Inside getAllProducts of ProductController");
        return ResponseEntity.ok(productService.getAllProducts().stream().map(productResponseMapper).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        log.info("Inside getProductById of ProductController");
        return ResponseEntity.ok(productResponseMapper.apply(productService.getProductById(id)));
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        log.info("Inside getAllCategories of ProductController");
        return ResponseEntity.ok(productService.getAllCategories().stream().map(categoryResponseMapper).collect(Collectors.toList()));
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long id) {
        log.info("Inside getCategoryById of ProductController");
        return ResponseEntity.ok(categoryResponseMapper.apply(categoryService.getCategoryById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Inside createProduct of ProductController");
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PostMapping("/category/create")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        log.info("Inside createCategory of ProductController");
        return new ResponseEntity<>(categoryResponseMapper.apply(categoryService.createCategory(categoryRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductRequest productRequest) {
        log.info("Inside updateProduct of ProductController");
        return ResponseEntity.ok(productResponseMapper.apply(productService.updateProduct(id, productRequest)));
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long id, @RequestBody @Valid CategoryRequest categoryRequest) {
        log.info("Inside updateCategory of ProductController");
        return ResponseEntity.ok(categoryResponseMapper.apply(categoryService.updateCategory(id, categoryRequest)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        log.info("Inside deleteProduct of ProductController");
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        log.info("Inside deleteCategory of ProductController");
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @PutMapping("/update/quantity")
    public ResponseEntity<String> updateStock(@RequestBody ProductList productList) {
        log.info("Inside updateStock of ProductController");
        productService.updateStock(productList.getProducts());
        return ResponseEntity.ok("Stock updated successfully");
    }
}
