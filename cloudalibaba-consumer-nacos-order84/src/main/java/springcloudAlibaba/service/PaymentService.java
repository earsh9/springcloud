package springcloudAlibaba.service;

/*
* openFeign : 接口 + 注解
*
* */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)  //按照 value 对应的命名空间去找相对应的服务。而不用注入 restTemplate 再调 getForObject; fallback为兜底降级
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")             //和 9003 的 getMapping 一样
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
