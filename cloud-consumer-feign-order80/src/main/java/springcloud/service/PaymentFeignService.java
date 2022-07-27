package springcloud.service;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

/*
* 接口 + 注解 (@FeignClient)
* */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")           //声明一下去 Eureka 找哪个微服务
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //模拟客户端与服务端之间的超时情况：Read timed out executing GET http://CLOUD-PAYMENT-SERVICE/payment/feign/timeout
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeOut();
}

/*
* 当客户端通过 controller 层 OrderFeignController 调用 getPaymentById 请求时
* 内部(80端口) Service 层接口 paymentFeignService 利用 @FeignClient 注解在 Eureka 找到对应名称 ("CLOUD-PAYMENT-SERVICE") 的微服务
* 其调用地址就是 8001、8002 对外暴露的 服务端口地址。
* 相当于中间隔了一层 feign 的东西
* */

