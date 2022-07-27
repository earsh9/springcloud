package springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
* 相比较在 controller 方法里为所有请求设置 兜底方法 @DefaultProperties
* 不如直接在源头，即 openfeign 的接口中设置：
* 新建类 PaymentFallbackService 实现当前接口，即可对当前接口内所有方法进行统一处理
* */
@Component
@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)     //通过value找到对应服务，执行下述方法，出了问题 fallback 类中的方法实现兜底
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
