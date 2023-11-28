package com.basnetpr.shoppingcart.dto;

import com.basnetpr.shoppingcart.entity.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;

@Service
@Slf4j
public class ShoppingCartResponseMapper implements Function<ShoppingCart, ShoppingCartResponse> {
    @Override
    public ShoppingCartResponse apply(ShoppingCart shoppingCart) {
        log.info("Inside ShoppingCartDtoMapper");
        return new ShoppingCartResponse(
                shoppingCart.getId(),
                shoppingCart.getCartItems() != null ? shoppingCart.getCartItems() : new ArrayList<>(),
                shoppingCart.getUserId());
    }
}
