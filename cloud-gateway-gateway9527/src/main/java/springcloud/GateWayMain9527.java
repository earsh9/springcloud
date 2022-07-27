package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
网关就是看大门的，不用写业务类
* 在真实访问路径之前，套一层 9527 端口，如此无需暴露真实端口 8001
  网关会根据路由：找到对应路径；断言：一些校验判断 进行转发访问
  路由 (Route) 和断言 (Predicate) 的配置方式：1.yml 2.硬编码到config包下
  过滤 (Filter)：可以在请求被路由前或者之后对请求进行修改。一样在 yml 中添加 filters:...
  自定义 全局 GlobalFilter
* */

/*
* 之前是 ribbon 时间微服务的负载均衡，现在 gateway 拦在微服务之前 ---> 让 gateway 实现负载均衡和动态路由
* 1. yml 中配置 discovery:locator:enabled: true
* 2. uri 中的端口不能写死：前缀加 lb 以区分是动态路由：lb://cloud-payment-service 换成在服务注册中心Eureka内注册的服务名称
* */

@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527 {

    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}
