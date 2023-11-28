package com.basnetpr.order.service;

import com.basnetpr.order.client.ProductClientService;
import com.basnetpr.order.dto.OrderRequest;
import com.basnetpr.order.dto.OrderResponse;
import com.basnetpr.order.dto.OrderResponseMapper;
import com.basnetpr.order.dto.ProductList;
import com.basnetpr.order.entity.Order;
import com.basnetpr.order.entity.OrderItem;
import com.basnetpr.order.respository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderResponseMapper orderDTOMapper;
    private final ProductClientService productClientService;

    public List<OrderResponse> getAllOrders() {
        log.info("Inside getAllOrders of OrderService");
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderDTOMapper).collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Long id) {
        log.info("Inside getOrderById of OrderService");
        Order order = orderRepository.findById(id).orElseThrow();
        return orderDTOMapper.apply(order);
    }

    public OrderResponse createOrder(OrderRequest orderRequest) {
        log.info("Inside createOrder of OrderService");
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .paymentId(orderRequest.getPaymentId())
                .totalPrice(orderRequest.getTotalPrice())
                .shippingAddress(orderRequest.getShippingAddress())
                .paymentMethod(orderRequest.getPaymentMethod())
                .orderDate(LocalDate.now())
                .orderStatus(orderRequest.getOrderStatus())
                .build();
        List<OrderItem> orderItems = orderRequest
                .getOrderItems()
                .stream().map(orderItemRequest -> OrderItem.builder().productId(orderItemRequest.getProductId())
                        .quantity(orderItemRequest.getQuantity()).price(orderItemRequest.getPrice())
                        .order(order)
                        .build())
                        .toList();
        order.setOrderItems(orderItems);
        manageProduct(orderItems);
        return orderDTOMapper.apply(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        log.info("Inside deleteOrder of OrderService");
        orderRepository.deleteById(id);
    }

    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        log.info("Inside updateOrder of OrderService");
        Order oldOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .paymentId(orderRequest.getPaymentId())
                .totalPrice(orderRequest.getTotalPrice())
                .shippingAddress(orderRequest.getShippingAddress())
                .paymentMethod(orderRequest.getPaymentMethod())
                .build();
        order.setId(id);
        order.setOrderDate(oldOrder.getOrderDate());
        order.setOrderItems(oldOrder.getOrderItems());
        return orderDTOMapper.apply(orderRepository.save(order));
    }

    public List<OrderResponse> getOrderByUserId(Long userId) {
        log.info("Inside getOrderByUserId of OrderService");
        List<Order> orders = orderRepository.findOrderByUserId(userId);
        return orders.stream().map(orderDTOMapper).collect(Collectors.toList());
    }

    public void manageProduct(List<OrderItem> orderItems) {
        log.info("Inside manageProduct of OrderService");
        Map<Long, Long> productQuantityMap = new HashMap<>();
        orderItems.forEach(orderItem -> productQuantityMap.put(orderItem.getProductId(), orderItem.getQuantity()));
        ProductList productList = ProductList.builder().products(productQuantityMap).build();
        String response = productClientService.updateProductQuantity(productList);
        log.info("Response from product service: {}", response);
    }

}
