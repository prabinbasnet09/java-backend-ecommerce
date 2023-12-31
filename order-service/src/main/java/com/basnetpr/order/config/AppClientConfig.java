package com.basnetpr.order.config;

import com.basnetpr.order.client.ProductClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class AppClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().
                baseUrl("http://localhost:9001").build();
    }

    @Bean
    public ProductClientService productClientService() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient()))
                .build();
        return httpServiceProxyFactory.createClient(ProductClientService.class);
    }
}
