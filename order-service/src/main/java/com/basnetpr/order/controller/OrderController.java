package com.basnetpr.order.controller;

import com.basnetpr.order.client.ProductClientService;
import com.basnetpr.order.dto.OrderItemRequest;
import com.basnetpr.order.dto.OrderRequest;
import com.basnetpr.order.dto.OrderResponse;
import com.basnetpr.order.dto.OrderItemResponse;
import com.basnetpr.order.entity.Order;
import com.basnetpr.order.entity.OrderItem;
import com.basnetpr.order.service.OrderItemService;
import com.basnetpr.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final ProductClientService productClientService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrderByUserId(@PathVariable("userId") Long userId) {
        log.info("Inside getOrderByUserId of OrderController");
        return ResponseEntity.ok(orderService.getOrderByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("Inside getAllOrders of OrderController");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        log.info("Inside getOrderById of OrderController");
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/{id}/orderItems")
    public ResponseEntity<List<OrderItemResponse>> getOrderItemsByOrderId(@PathVariable("id") Long id) {
        log.info("Inside getOrderItemsByOrderId of OrderController");
        return ResponseEntity.ok(orderItemService.getOrderItemsByOrderId(id));
    }

    @GetMapping("/{id}/orderItems/{itemId}")
    public ResponseEntity<OrderItemResponse> getOrderItemByOrderIdAndItemId(@PathVariable("id") Long id, @PathVariable Long itemId) {
        log.info("Inside getOrderItemByOrderIdAndItemId of OrderController");
        return ResponseEntity.ok(orderItemService.getOrderItemByOrderIdAndItemId(id, itemId));
    }

    @PostMapping("/")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Inside createOrder of OrderController");
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/orderItems")
    public ResponseEntity<OrderItemResponse> createOrderItem(@PathVariable("id") Long id, @RequestBody @Valid OrderItemRequest orderItemRequest) {
        log.info("Inside createOrderItem of OrderController");
        return new ResponseEntity<>(orderItemService.createOrderItem(id, orderItemRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        log.info("Inside deleteOrder of OrderController");
        orderService.deleteOrder(id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/orderItems/delete/{itemId}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId) {
        log.info("Inside deleteOrderItem of OrderController");
        orderItemService.deleteOrderItem(id, itemId);
        return new ResponseEntity<>("Order item deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") Long id, @RequestBody @Valid OrderRequest orderRequest) {
        log.info("Inside updateOrder of OrderController");
        return new ResponseEntity<>(orderService.updateOrder(id, orderRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}/orderItems/update/{itemId}")
    public ResponseEntity<OrderItemResponse> updateOrderItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, @RequestBody @Valid OrderItemRequest orderItemRequest) {
        log.info("Inside updateOrderItem of OrderController");
        return new ResponseEntity<>(orderItemService.updateOrderItem(id, itemId, orderItemRequest), HttpStatus.OK);
    }

}
