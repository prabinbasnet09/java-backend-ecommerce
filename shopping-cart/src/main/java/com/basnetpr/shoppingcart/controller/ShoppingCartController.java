package com.basnetpr.shoppingcart.controller;

import com.basnetpr.shoppingcart.dto.CartItemRequest;
import com.basnetpr.shoppingcart.dto.CartItemResponse;
import com.basnetpr.shoppingcart.dto.ShoppingCartRequest;
import com.basnetpr.shoppingcart.dto.ShoppingCartResponse;
import com.basnetpr.shoppingcart.entity.CartItem;
import com.basnetpr.shoppingcart.entity.ShoppingCart;
import com.basnetpr.shoppingcart.service.CartItemService;
import com.basnetpr.shoppingcart.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart")
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @GetMapping("{userId}")
    public ResponseEntity<List<ShoppingCartResponse>> getCartByUserId(@PathVariable("userId") Long userId) {
        log.info("Inside getCartByUserId of ShoppingCartController");
        return ResponseEntity.ok(shoppingCartService.getCartByUserId(userId));
    }

    @GetMapping("/carts")
    public ResponseEntity<List<ShoppingCartResponse>> getAllCarts() {
        log.info("Inside getAllCarts of ShoppingCartController");
        return ResponseEntity.ok(shoppingCartService.getAllCarts());
    }

    @GetMapping("/carts/{cartId}")
    public ResponseEntity<ShoppingCartResponse> getCartById(@PathVariable("cartId") Long cartId) {
        log.info("Inside getCartById of ShoppingCartController");
        return ResponseEntity.ok(shoppingCartService.getCartById(cartId));
    }

    @GetMapping("/carts/{cartId}/cart-items")
    public ResponseEntity<List<CartItemResponse>> getAllCartItems(@PathVariable("cartId") Long cartId){
        log.info("Inside getAllCartItems of ShoppingCartController");
        return ResponseEntity.ok(cartItemService.getAllCartItems(cartId));
    }

    @GetMapping("/carts/{cartId}/cart-items/{cartItemId}")
    public ResponseEntity<CartItemResponse> getCartItemById(@PathVariable("cartItemId") Long cartItemId){
        log.info("Inside getCartItemById of ShoppingCartController");
        return ResponseEntity.ok(cartItemService.getCartItemById(cartItemId));
    }

    @PostMapping("/carts")
    public ResponseEntity<ShoppingCartResponse> createCart(@RequestBody @Valid ShoppingCartRequest shoppingCartRequest) {
        log.info("Inside createCart of ShoppingCartController");
        return new ResponseEntity<>(shoppingCartService.createCart(shoppingCartRequest), HttpStatusCode.valueOf(201));
    }

    @PostMapping("/carts/{cartId}/cart-items")
    public ResponseEntity<CartItemResponse> createCartItem(@PathVariable("cartId") Long cartId, @RequestBody @Valid CartItemRequest cartItemRequest) {
        log.info("Inside createCartItem of ShoppingCartController");
        return new ResponseEntity<>(cartItemService.createCartItem(cartId, cartItemRequest), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/carts/{cartId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteCart(@PathVariable("cartId") Long cartId) {
        log.info("Inside deleteCart of ShoppingCartController");
        shoppingCartService.deleteCart(cartId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/carts/{cartId}/cart-items/{cartItemId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteCartItem(@PathVariable("cartId") Long cartId, @PathVariable("cartItemId") Long cartItemId) {
        log.info("Inside deleteCartItem of ShoppingCartController");
        cartItemService.deleteCartItem(cartId, cartItemId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("/carts/{cartId}/cart-items/{cartItemId}")
    public ResponseEntity<CartItemResponse> updateCartItem(@PathVariable("cartId") Long cartId, @PathVariable("cartItemId") Long cartItemId, @RequestBody @Valid CartItemRequest cartItemRequest) {
        log.info("Inside updateCartItem of ShoppingCartController");
        return new ResponseEntity<>(cartItemService.updateCartItem(cartId, cartItemId, cartItemRequest), HttpStatus.OK);
    }


}
