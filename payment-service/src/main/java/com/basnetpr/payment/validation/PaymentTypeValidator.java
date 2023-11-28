package com.basnetpr.payment.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaymentTypeValidator implements ConstraintValidator<ValidatePaymentType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("CREDIT") || value.equals("DEBIT");
    }
}
