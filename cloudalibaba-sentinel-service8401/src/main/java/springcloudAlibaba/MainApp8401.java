package springcloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
* 既要注册进 8848端口的 nacos，也要被 8080端口的 sentinel 所监控
* 演示 Alibaba sentinel 流量控制、服务降级
* 并将 sentinel 规则持久化到 nacos （以前都是服务重启就得重新配规则）（持久化到哪儿都行，只要是个持久化的媒介）
 * */

@EnableDiscoveryClient
@SpringBootApplication
public class MainApp8401
{
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class, args);
    }
}

