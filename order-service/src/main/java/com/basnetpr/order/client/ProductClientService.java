package com.basnetpr.order.client;

import com.basnetpr.order.dto.ProductList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.Map;

@HttpExchange
public interface ProductClientService {

    @PutExchange("products/update/quantity")
    String updateProductQuantity(@RequestBody ProductList productList);
}
