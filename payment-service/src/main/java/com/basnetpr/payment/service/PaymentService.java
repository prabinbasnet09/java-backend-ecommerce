package com.basnetpr.payment.service;

import com.basnetpr.payment.dto.PaymentRequest;
import com.basnetpr.payment.dto.PaymentResponse;
import com.basnetpr.payment.dto.PaymentResponseMapper;
import com.basnetpr.payment.entity.Payment;
import com.basnetpr.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentResponseMapper paymentResponseMapper;
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(PaymentRequest paymentRequest) {
        Payment payment = Payment.builder()
                .userId(paymentRequest.getUserId())
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .status(paymentRequest.getStatus())
                .build();
        return paymentRepository.save(payment);
    }

    public void updatePayment(Long paymentId, PaymentRequest paymentRequest) {
        Payment existingPayment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        existingPayment.setAmount(paymentRequest.getAmount());
        existingPayment.setPaymentMethod(paymentRequest.getPaymentMethod());
        existingPayment.setStatus(paymentRequest.getStatus());
        paymentRepository.save(existingPayment);
    }

    public List<PaymentResponse> getPaymentByUserId(Long userId) {
        return paymentRepository.findByUserId(userId).stream().map(paymentResponseMapper).collect(Collectors.toList());
    }
}
