package com.basnetpr.product.service;

import com.basnetpr.product.dto.ProductRequest;
import com.basnetpr.product.dto.ProductResponse;
import com.basnetpr.product.dto.ProductResponseMapper;
import com.basnetpr.product.entity.Category;
import com.basnetpr.product.entity.Product;
import com.basnetpr.product.repository.CategoryRepository;
import com.basnetpr.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final ProductResponseMapper productResponseMapper;
    public List<Product> getAllProducts() {
        log.info("Inside getAllProducts of ProductService");
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        log.info("Inside getProductById of ProductService");
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("Inside createProduct of ProductService");
        Category category = categoryService.getCategoryById(productRequest.getCategoryId());
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productDescription(productRequest.getProductDescription())
                .productPrice(productRequest.getProductPrice())
                .productQuantity(productRequest.getProductQuantity())
                .productCategory(category)
                .build();
        category.getProducts().add(product);
        return productResponseMapper.apply(categoryRepository.save(category).getProducts().get(category.getProducts().size() - 1));
    }

    public List<Category> getAllCategories() {
        log.info("Inside getAllCategories of ProductService");
        return categoryRepository.findAll();
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        log.info("Inside updateProduct of ProductService");
        Product product = getProductById(id);
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setProductQuantity(productRequest.getProductQuantity());

        if(!productRequest.getCategoryId().equals(product.getProductCategory().getCategoryId())) {
            Category category = categoryService.getCategoryById(productRequest.getCategoryId());
            product.setProductCategory(category);
            category.getProducts().add(product);
            categoryRepository.save(category);
        }
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        log.info("Inside deleteProduct of ProductService");
        Product product = getProductById(id);
        Category category = product.getProductCategory();
        category.getProducts().remove(product);
        categoryRepository.save(category);
        productRepository.delete(product);
    }

    public void updateStock(Map<Long, Long> productList) {
        log.info("Inside updateStock of ProductService");
        productList.forEach(this::updateStock);

    }

    public void updateStock(Long productId, Long quantity) {
        log.info("Inside updateStock of ProductService");
        Product product = getProductById(productId);
        product.setProductQuantity(product.getProductQuantity() - quantity);
        productRepository.save(product);
    }
}
