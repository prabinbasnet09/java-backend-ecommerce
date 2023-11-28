package com.basnetpr.payment.dto;

import com.basnetpr.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PaymentResponseMapper implements Function<Payment, PaymentResponse> {

    @Override
    public PaymentResponse apply(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getPaymentMethod());
    }
}
