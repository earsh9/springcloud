package springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    /**
     * 配置了一个id为route-name的路由规则，
     * 当访问地址 http://localhost:9527/guonei 时会自动转发到地址：http://news.baidu.com/guonei
     */

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("transfer_news_baidu",
                r -> r.path("/guonei")          //java8 新特性：应用型函数，有输入有返回；创建 http://localhost:9527/guonei 与 http://news.baidu.com/guonei 的映射关系
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
