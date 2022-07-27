package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
* 在 config 基础上引入消息总线 bus，更进一步实现动态刷新 （之前的还需要手动发送 Post 以更新客户端）
* 演示广播效果，增加复杂度，再以3355为模板再制作一个3366
* */
@SpringBootApplication
@EnableEurekaClient
public class ConfigClientMain3366 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class, args);
    }
}
