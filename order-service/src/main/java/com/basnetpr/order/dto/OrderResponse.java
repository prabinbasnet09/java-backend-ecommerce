package com.basnetpr.order.dto;

import com.basnetpr.order.entity.OrderItem;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(Long id, Long paymentId, Long userId, Double totalPrice, List<OrderItem> orderItems, LocalDate OrderDate, String shippingAddress, String paymentMethod) {
}
