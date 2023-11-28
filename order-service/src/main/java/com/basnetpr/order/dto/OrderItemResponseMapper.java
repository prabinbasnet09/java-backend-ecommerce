package com.basnetpr.order.dto;

import com.basnetpr.order.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderItemResponseMapper implements Function<OrderItem, OrderItemResponse> {
    @Override
    public OrderItemResponse apply(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
