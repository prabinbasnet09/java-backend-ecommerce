package com.basnetpr.payment.controller;

import com.basnetpr.payment.dto.PaymentRequest;
import com.basnetpr.payment.dto.PaymentResponse;
import com.basnetpr.payment.dto.PaymentResponseMapper;
import com.basnetpr.payment.entity.Payment;
import com.basnetpr.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentResponseMapper paymentResponseMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentDetailsByUserId(@PathVariable("userId") Long userId) {
        log.info("Inside getPaymentDetailsByUserId of PaymentController");
        return ResponseEntity.ok(paymentService.getPaymentByUserId(userId));
    }
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsById(@PathVariable("paymentId") Long paymentId) {
        log.info("Inside getPaymentDetailsById of PaymentController");
        return ResponseEntity.ok(paymentResponseMapper.apply(paymentService.getPaymentById(paymentId)));
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        log.info("Inside createPayment of PaymentController");
        return new ResponseEntity<>(paymentResponseMapper.apply(paymentService.createPayment(paymentRequest)) , HttpStatus.CREATED);
    }

    @PutMapping("/{paymentId}/update")
    public ResponseEntity<String> updatePayment(@PathVariable("paymentId") Long paymentId, @RequestBody @Valid PaymentRequest paymentRequest) {
        log.info("Inside updatePayment of PaymentController");
        paymentService.updatePayment(paymentId, paymentRequest);
        return ResponseEntity.ok("Payment updated successfully");
    }
}
