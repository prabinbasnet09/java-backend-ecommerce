package com.basnetpr.cloud.gateway.filter;

import com.basnetpr.cloud.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
        ServerHttpRequest request = exchange.getRequest();
            if(routeValidator.isSecured.test(exchange.getRequest())){
                //check if it contains header or not
                if(!exchange.getRequest().getHeaders().containsKey("Authorization")){
                    throw new RuntimeException("Authorization header is missing");
                }

                //get the token
                String authorizationHeader = exchange.getRequest().getHeaders().get("Authorization").get(0);
                if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
                    throw new RuntimeException("Authorization header is invalid");
                }
                authorizationHeader = authorizationHeader.substring(7);


                //either do a rest call to validate token or configure a validation class inside the gateway
                //below will validate token without rest call
                try{
                    jwtUtil.validateToken(authorizationHeader);
                    request = request
                            .mutate()
                            .header("username", jwtUtil.extractUsername(authorizationHeader))
                            .build();
                }
                catch(Exception e){
                    throw new RuntimeException("Invalid token");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config{

    }
}
