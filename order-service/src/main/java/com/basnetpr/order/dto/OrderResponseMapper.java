package com.basnetpr.order.dto;

import com.basnetpr.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderResponseMapper implements Function<Order, OrderResponse> {
    @Override
    public OrderResponse apply(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getPaymentId(),
                order.getTotalPrice(),
                order.getOrderItems(),
                order.getOrderDate(),
                order.getShippingAddress(),
                order.getPaymentMethod()
        );
    }
}
