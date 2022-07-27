package springcloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/*
* 创建消费端 84，服务提供端 9003、9004。均注册进 nacos
* 使用 sentinel
* 结合 ribbon 进行负载均衡（轮询 9003 和 9004）
* 结合 feign 演示 sentinel 限流跟熔断既可以使用在服务侧也可以使用在消费侧 （当只有 84 和 9003 时。可以正常访问到数据，当 9003 挂了，消费侧即客户端也能实现 服务降级）
* */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients             //激活 feign
public class OrderNacosMain84
{
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }
}
