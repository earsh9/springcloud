package springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
* 配置好 restTemplate，以便 controller 层注入
* 由于 Payment 服务端使用了集群，整个服务集群对外暴漏的是 微服务名称即 CLOUD-PAYMENT-SERVICE
* 但对内并不知道哪台机器真正执行了操作 ---> 使用 RestTemplate 的 @LoadBalanced 实现负载均衡 (分配端口) 默认轮询
* */

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced         //测试自己写的负载均衡算法
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
