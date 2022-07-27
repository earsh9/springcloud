package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
* config client:
* 1. 要解决分布式配置的 动态刷新 问题：github 修改配置后 3344 能立刻刷新，但 3355 需要重启才行
* --> 添加 actuator 监控 (有修改时能被监控到。除了网关其他都需要)
* */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3355 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
