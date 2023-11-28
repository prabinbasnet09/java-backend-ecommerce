package com.basnetpr.shoppingcart.service;

import com.basnetpr.shoppingcart.dto.CartItemRequest;
import com.basnetpr.shoppingcart.dto.CartItemResponse;
import com.basnetpr.shoppingcart.dto.CartItemResponseMapper;
import com.basnetpr.shoppingcart.entity.CartItem;
import com.basnetpr.shoppingcart.entity.ShoppingCart;
import com.basnetpr.shoppingcart.repository.CartItemRepository;
import com.basnetpr.shoppingcart.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemResponseMapper cartItemResponseMapper;
    public List<CartItemResponse> getAllCartItems(Long cartId) {
        log.info("Inside getAllCartItems of CartItemService");
        return cartItemRepository.findAllByShoppingCartId(cartId)
                .stream()
                .map(cartItemResponseMapper)
                .collect(Collectors.toList());
    }

    public CartItemResponse getCartItemById(Long cartItemId) {
        log.info("Inside getCartItemById of CartItemService");
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);
        if(cartItem == null) return null;
        return cartItemResponseMapper.apply(cartItem);
    }

    public CartItemResponse createCartItem(Long cartId, CartItemRequest cartItemRequest) {
        log.info("Inside createCartItem of CartItemService");
        CartItem cartItem = CartItem.builder()
                .productId(cartItemRequest.getProductId())
                .quantity(cartItemRequest.getQuantity())
                .build();
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        if(shoppingCart == null) return null;
        shoppingCart.getCartItems().add(cartItem);
        cartItem.setShoppingCart(shoppingCart);
        CartItem cartItem1 = shoppingCartRepository.save(shoppingCart).getCartItems().get(shoppingCart.getCartItems().size()-1);
        return cartItemResponseMapper.apply(cartItem1);
    }

    public CartItemResponse updateCartItem(Long cartId, Long cartItemId, CartItemRequest cartItemRequest) {
        log.info("Inside updateCartItem of CartItemService");
        CartItem cartItem = CartItem.builder()
                .productId(cartItemRequest.getProductId())
                .quantity(cartItemRequest.getQuantity())
                .build();
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        if(shoppingCart == null) return null;
        CartItem cartItem1 = cartItemRepository.findById(cartItemId).orElse(null);
        if(cartItem1 == null) return null;
        cartItem1.setQuantity(cartItem.getQuantity());
        cartItem1.setProductId(cartItem.getProductId());
        cartItem1.setShoppingCart(shoppingCart);
        CartItem cartItem2 = cartItemRepository.save(cartItem1);
        return cartItemResponseMapper.apply(cartItem2);
    }

    public void deleteCartItem(Long cartId, Long cartItemId) {
        log.info("Inside deleteCartItem of CartItemService");
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Shopping Cart not found"));
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("Cart Item not found"));
        shoppingCart.getCartItems().remove(cartItem);
        shoppingCartRepository.save(shoppingCart);
        cartItemRepository.deleteById(cartItemId);
    }
}
