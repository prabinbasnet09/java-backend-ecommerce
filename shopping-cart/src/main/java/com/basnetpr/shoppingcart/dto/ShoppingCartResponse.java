package com.basnetpr.shoppingcart.dto;

import com.basnetpr.shoppingcart.entity.CartItem;


import java.util.List;
public record ShoppingCartResponse(Long id, List<CartItem> cartItems, Long userId){
}
