package com.basnetpr.shoppingcart.service;

import com.basnetpr.shoppingcart.dto.ShoppingCartRequest;
import com.basnetpr.shoppingcart.dto.ShoppingCartResponse;
import com.basnetpr.shoppingcart.dto.ShoppingCartResponseMapper;
import com.basnetpr.shoppingcart.entity.ShoppingCart;
import com.basnetpr.shoppingcart.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartResponseMapper shoppingCartDTOMapper;

    public List<ShoppingCartResponse> getAllCarts() {
        log.info("Inside getAllCarts of ShoppingCartService");
        return shoppingCartRepository.findAll().stream()
                .map(shoppingCartDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public ShoppingCartResponse getCartById(Long id) {
        log.info("Inside getCartById of ShoppingCartService");
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        log.info("ShoppingCart: {}", shoppingCart);
        if(shoppingCart == null) return null;
        return shoppingCartDTOMapper.apply(shoppingCart);
    }

    public ShoppingCartResponse createCart(ShoppingCartRequest shoppingCartRequest) {
        log.info("Inside createCart of ShoppingCartService");
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(shoppingCartRequest.getUserId())
                .build();
        ShoppingCart shoppingCart1 =  shoppingCartRepository.save(shoppingCart);
        return shoppingCartDTOMapper.apply(shoppingCart1);
    }

    public void deleteCart(Long cartId) {
        log.info("Inside deleteCart of ShoppingCartService");
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId).orElse(null);
        if(shoppingCart == null) return;
        shoppingCartRepository.deleteById(cartId);
    }

    public List<ShoppingCartResponse> getCartByUserId(Long userId) {
        log.info("Inside getCartByUserId of ShoppingCartService");
        return shoppingCartRepository.findByUserId(userId).stream()
                .map(shoppingCartDTOMapper)
                .collect(Collectors.toList());
    }
}
