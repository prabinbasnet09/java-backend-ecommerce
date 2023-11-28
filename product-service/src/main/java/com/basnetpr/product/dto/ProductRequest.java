package com.basnetpr.product.dto;

import com.basnetpr.product.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Product name cannot be null or empty")
    private String productName;
    private String productDescription;
    @NotNull(message = "Product price cannot be null or empty")
    private Double productPrice;
    @NotNull(message = "Product quantity cannot be null or empty")
    private Long productQuantity;
    @NotNull(message = "Product category id cannot be null or empty")
    private Long categoryId;
}
