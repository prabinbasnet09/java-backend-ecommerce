package com.basnetpr.order.dto;

public record OrderItemResponse(Long id, Long productId, Long quantity, Double price) {
}
