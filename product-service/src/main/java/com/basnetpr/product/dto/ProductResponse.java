package com.basnetpr.product.dto;

public record ProductResponse(Long productId, String productName, String productDescription, Double productPrice,
                              Long productQuantity, Long categoryId) {

}