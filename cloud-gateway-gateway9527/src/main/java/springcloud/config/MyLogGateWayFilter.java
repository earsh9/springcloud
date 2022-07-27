package springcloud.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/*
* 自定义过滤器:
* 1. 实现两个接口
* */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {        //exchange可以理解为 request/response
        log.info("come in MyLogGateWayFilter ... " + new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){
            log.info("******用户名为 null, 非法用户，/(ㄒoㄒ)/~~");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);        //更新状态为不被接受的
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);          //合法用户，放行；转去下一个 filter
    }

    @Override
    public int getOrder() {         //加载过滤器的顺序，值越小优先级越高
        return 0;
    }
}
