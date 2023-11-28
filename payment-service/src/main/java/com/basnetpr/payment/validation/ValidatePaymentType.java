package com.basnetpr.payment.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentTypeValidator.class)
public @interface ValidatePaymentType {
    public String message() default "Payment type must be either CREDIT or DEBIT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
