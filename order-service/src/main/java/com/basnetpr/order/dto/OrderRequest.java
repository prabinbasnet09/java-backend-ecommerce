package com.basnetpr.order.dto;

import com.basnetpr.order.entity.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = "User id cannot be null")
    private Long userId;
    @NotNull(message = "Payment id cannot be null")
    private Long paymentId;
    @NotNull(message = "Total price cannot be null")
    private Double totalPrice;
    @NotNull(message = "Shipping Address cannot be null")
    private String shippingAddress;
    @NotNull(message = "Payment method cannot be null")
    private String paymentMethod;
    private String orderStatus;
    @NotNull(message = "Order items cannot be null")
    private List<OrderItemRequest> orderItems;
}
