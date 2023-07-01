package com.kyrie.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component    //把过滤器交给spring管理才生效
@Order(-1)    //作用是用来在过滤器链中表示执行顺序，传的数字越小优先级越高
public class MyGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //exchange中包含了request和response
        ServerHttpRequest request = exchange.getRequest();

        /*
         * 这里的拦截逻辑是：判断请求中是否有username参数，有且参数为admin则放行
         * 没有就拦截
         */
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //读出请求key为username的请求
        String username = queryParams.getFirst("username");
        //如果key有username且值为admin则放行
        if (username.equals("admin")) {
            return chain.filter(exchange);
        }

        /**
         * 没有满足条件，那么请求就再gateway网关这个全局拦截器拦截
         * 直接响应response，已经complete了
         */
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); //异常状态码为401
        return exchange.getResponse().setComplete();
    }
}
