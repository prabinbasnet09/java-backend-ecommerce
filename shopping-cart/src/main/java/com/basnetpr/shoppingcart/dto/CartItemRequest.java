package com.basnetpr.shoppingcart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequest {
    @NotNull(message = "Product id cannot be null")
    private Long productId;
    @NotNull(message = "Quantity cannot be null")
    private Long quantity;
}
