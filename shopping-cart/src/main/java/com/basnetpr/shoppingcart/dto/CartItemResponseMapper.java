package com.basnetpr.shoppingcart.dto;

import com.basnetpr.shoppingcart.entity.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class CartItemResponseMapper implements Function<CartItem, CartItemResponse> {
    @Override
    public CartItemResponse apply(CartItem cartItem) {
        log.info("Inside CartItemResponseMapper");
        return new CartItemResponse(cartItem.getId(), cartItem.getQuantity(), cartItem.getProductId());
    }
}

