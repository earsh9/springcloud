package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
* 服务注册中心 就是来登记注册，其内不会用任何业务逻辑类
* (物业公司：需要等待公司 (8001 服务端) 来注册, 以等待客户端 (80) 找到)
* */
@SpringBootApplication
@EnableEurekaServer                 //Eureka 有俩中间件，使用该注解标识其为 服务注册中心的 服务端; 前来注册的公司是 客户端。服务端要给客户端提供服务以便被其他 order客户端发现并访问
public class EurekaMain7002 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class, args);
    }
}
