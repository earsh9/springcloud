package springcloudAlibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloudAlibaba.service.PaymentService;

import javax.annotation.Resource;

/*
* fallback: 管的是 Java 代码的运行时异常
* BlockHandler: 管的是违反 sentinel 配置规则的异常
*
* */
@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")             //啥都不配置时，当传入 id 不为1,2,3，由于java业务异常自动跳转至原生error页面。不友好
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")          //fallback先把java本身业务异常兜底了
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")          //blockHandler 是管 sentinel 配置规则违规时异常
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",//两个都配，当运行时异常达到sentinel配置规则时也交由 blockHandler 处理
            exceptionsToIgnore = {IllegalArgumentException.class})      //忽略异常，若报当前异常且将其忽略 --> 兜底方法不管用，error page 直接给到用户
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    public CommonResult handlerFallback(@PathVariable  Long id,Throwable e) {       //java 业务异常的 fallback 兜底方法 (服务降级)
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    public CommonResult blockHandler(@PathVariable  Long id, BlockException blockException) {       //负责违反 sentinel 配置规则时的异常处理方法
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }



    //================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
