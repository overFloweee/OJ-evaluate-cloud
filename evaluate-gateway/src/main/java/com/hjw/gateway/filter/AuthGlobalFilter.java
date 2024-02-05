package com.hjw.gateway.filter;

import cn.hutool.core.text.AntPathMatcher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered
{
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        // 1.获取request
        ServerHttpRequest request = exchange.getRequest();

        // 2.判断是否需要作登录拦截
        String path = request.getPath().toString();

        // 内部路径屏蔽
        if (antPathMatcher.match("/**/inner/**", path))
        {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}
