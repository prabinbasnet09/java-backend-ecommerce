package com.basnetpr.payment.dto;

import com.basnetpr.payment.entity.PaymentStatus;

public record PaymentResponse(Long id, Long userId, Double amount, PaymentStatus status, String paymentMethod) {
}