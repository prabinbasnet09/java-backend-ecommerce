package com.basnetpr.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    @NotNull(message = "Product id cannot be null or empty")
    private Long productId;
    @NotNull(message = "Order id cannot be null")
    private Long orderId;
    @NotNull(message = "Quantity cannot be null")
    private Long quantity;
    @NotNull(message = "Price cannot be null")
    private Double price;
}
