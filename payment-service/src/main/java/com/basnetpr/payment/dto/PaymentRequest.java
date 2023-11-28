package com.basnetpr.payment.dto;

import com.basnetpr.payment.entity.PaymentStatus;
import com.basnetpr.payment.validation.ValidatePaymentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    @NotNull(message = "User id cannot be null")
    private Long userId;
    @NotNull(message = "Amount cannot be null")
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @ValidatePaymentType
    private String paymentMethod;
}
