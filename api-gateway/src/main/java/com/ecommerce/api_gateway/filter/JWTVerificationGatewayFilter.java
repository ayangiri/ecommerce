package com.ecommerce.api_gateway.filter;

import com.ecommerce.api_gateway.model.AuthorizationRequest;
import com.ecommerce.api_gateway.model.TokenDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTVerificationGatewayFilter implements GlobalFilter, Ordered {
    private RestTemplate restTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Inside jwt verification filter");
        ServerHttpRequest request = exchange.getRequest();
        try {
            if (!shouldFilter(request)) {
                Optional<String> authorizationHeader = getAuthorizationHeader(request);
                Optional<String> authorizationToken = getTokenFromAuthorizationHeader(authorizationHeader.get());
                if (!validateToken(authorizationToken.get())){
                    throw new RuntimeException("Invalid Token");
                }
            }
        } catch (Exception e) {
            log.error("Invalid Token");
            throw new RuntimeException("Invalid Token");
        }
        return chain.filter(exchange);
    }

    private boolean validateToken(String token){
        String url = "localhost:9095/auth/authenticate";
        HttpEntity<Object> httpEntity = new HttpEntity<>(new AuthorizationRequest(token),null);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if(exchange.getStatusCode().equals(HttpStatus.OK)){
            return true;
        }
        return false;

    }

    private boolean shouldFilter(ServerHttpRequest httpRequest) {
        String uri = httpRequest.getPath().toString();
        if (uri.startsWith("/users/signUp") ||
                uri.startsWith("/users/login")
        ) {
            return true;
        }
        return false;
    }

    private Optional<String> getAuthorizationHeader(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        if (headers != null && headers.containsKey("Authorization")) {
            List<String> authHeaders = headers.get("Authorization");
            if (!CollectionUtils.isEmpty(authHeaders)) {
                return Optional.ofNullable(authHeaders.get(0));
            }
        }
        return Optional.empty();
    }

    private Optional<String> getTokenFromAuthorizationHeader(String header) {
        String token = header.replace("Bearer ", "");
        return Optional.of(token.strip());
    }


    @Override
    public int getOrder() {
        return -1;
    }
}
