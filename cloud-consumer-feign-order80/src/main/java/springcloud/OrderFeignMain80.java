package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
* 相比较 ribbon + RestTemplate 模式的实现客户端负载均衡
* openFeign 进一步封装，可以通过 "接口 + 注解" 的方式，快捷实现简化了使用Spring cloud Ribbon时，自动封装服务调用客户端的开发量。
* 其内 jar 包自带 ribbon 天生实现负载均衡
 * */

@SpringBootApplication
@EnableFeignClients                     //注意该处注解换了
public class OrderFeignMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
