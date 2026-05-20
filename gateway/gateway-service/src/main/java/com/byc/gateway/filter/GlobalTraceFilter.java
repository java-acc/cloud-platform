package com.byc.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Stamps every incoming request with a trace id under header {@code X-Trace-Id} so downstream services can correlate
 * logs.
 */
@Component
public class GlobalTraceFilter implements GlobalFilter, Ordered {
    
    private static final String HEADER = "X-Trace-Id";
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = exchange.getRequest().getHeaders().getFirst(HEADER);
        if (traceId == null || traceId.isBlank()) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }
        final String tid = traceId;
        ServerHttpRequest mutated = exchange.getRequest().mutate().header(HEADER, tid).build();
        exchange.getResponse().getHeaders().add(HEADER, tid);
        return chain.filter(exchange.mutate().request(mutated).build());
    }
    
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
