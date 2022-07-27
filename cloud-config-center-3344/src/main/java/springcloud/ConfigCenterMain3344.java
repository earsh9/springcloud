package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
/*
* 总的配置中心 config server 3344：可以通过 Github 获取配置信息
* 对应的就有 config client 3355: 每次想要配置信息只找总的配置中心 3344 而非 Github
* */
/*
* 要想实现 config server 更新对应的所有或局部的 config client 也更新 ----> 引入消息总线 bus
* Spring Cloud Bus是用来将分布式系统的节点与轻量级消息系统链接起来的框架，
  它整合了Java的事件处理机制和消息中间件的功能。
  Spring Cloud Bus目前支持RabbitMQ和Kafka
* */

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
