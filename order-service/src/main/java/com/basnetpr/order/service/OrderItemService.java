package com.basnetpr.order.service;

import com.basnetpr.order.dto.OrderItemRequest;
import com.basnetpr.order.dto.OrderItemResponse;
import com.basnetpr.order.dto.OrderItemResponseMapper;
import com.basnetpr.order.entity.Order;
import com.basnetpr.order.entity.OrderItem;
import com.basnetpr.order.respository.OrderItemRepository;
import com.basnetpr.order.respository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemResponseMapper orderItemDTOMapper;
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItemResponse> getOrderItemsByOrderId(Long id) {
        log.info("Inside getOrderItemsByOrderId of OrderItemService");
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
        return orderItems.stream().map(orderItemDTOMapper).collect(Collectors.toList());
    }
    public OrderItemResponse getOrderItemByOrderIdAndItemId(Long id, Long itemId) {
        log.info("Inside getOrderItemByOrderIdAndItemId of OrderItemService");
        OrderItem orderItem = orderItemRepository.findById(itemId).orElseThrow();
        return orderItemDTOMapper.apply(orderItem);
    }

    public OrderItemResponse createOrderItem(Long id, OrderItemRequest orderItemRequest) {
        log.info("Inside createOrderItem of OrderItemService");
        OrderItem orderItem = OrderItem.builder()
                .quantity(orderItemRequest.getQuantity())
                .productId(orderItemRequest.getProductId())
                .price(orderItemRequest.getPrice())
                .build();
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderItem.setOrder(order);
        order.getOrderItems().add(orderItem);
        OrderItem orderItem1 = orderRepository.save(order).getOrderItems().get(order.getOrderItems().size() - 1);
        return orderItemDTOMapper.apply(orderItem1);
    }

    public void deleteOrderItem(Long id, Long itemId) {
        log.info("Inside deleteOrderItem of OrderItemService");
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        OrderItem orderItem = orderItemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("OrderItem not found"));
        order.getOrderItems().remove(orderItem);
        orderRepository.save(order);
        orderItemRepository.deleteById(itemId);
    }

    public OrderItemResponse updateOrderItem(Long id, Long itemId, OrderItemRequest orderItemRequests) {
        log.info("Inside updateOrderItem of OrderItemService");
        OrderItem orderItem = OrderItem.builder()
                .quantity(orderItemRequests.getQuantity())
                .productId(orderItemRequests.getProductId())
                .price(orderItemRequests.getPrice())
                .build();
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.getOrderItems().removeIf(orderItem1 -> orderItem1.getId().equals(itemId));
        orderItem.setId(itemId);
        orderItem.setOrder(order);
        order.getOrderItems().add(orderItem);
        OrderItem orderItem1 = orderRepository.save(order).getOrderItems().get(order.getOrderItems().size() - 1);
        return orderItemDTOMapper.apply(orderItem1);
    }
}
