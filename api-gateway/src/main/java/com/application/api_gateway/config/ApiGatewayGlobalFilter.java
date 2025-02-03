package com.application.api_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class ApiGatewayGlobalFilter implements GlobalFilter {

    private static final List<String> EXCLUDED_PATHS = List.of("/api/auth/login", "/api/auth/register");

    private final AddRequestHeaderGatewayFilterFactory addRequestHeaderGatewayFilterFactory;

    public ApiGatewayGlobalFilter(AddRequestHeaderGatewayFilterFactory addRequestHeaderGatewayFilterFactory) {
        this.addRequestHeaderGatewayFilterFactory = addRequestHeaderGatewayFilterFactory;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        if (EXCLUDED_PATHS.contains(path)) {
            log.info("Skipping token for path: {}", path);
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String jwtToken;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
        } else {
            jwtToken = "";
        }

        log.info("Processing request: {}", exchange.getRequest().getURI());
        log.info("Extracted JWT Token: {}", jwtToken);

        if (jwtToken == null || jwtToken.isEmpty()) {
            log.warn("No JWT token found in the request headers");
            return chain.filter(exchange);
        }

        GatewayFilter filter = addRequestHeaderGatewayFilterFactory.apply(config ->
                config.setName(HttpHeaders.AUTHORIZATION).setValue("Bearer " + jwtToken)
        );

        log.info("Applying filter: {}", filter.getClass().getName());
        return filter.filter(exchange, chain);
    }
}