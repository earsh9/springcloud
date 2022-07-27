package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;
import springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //前后端分离：此时返回给前端的应该是 json 数据
    //由于 controller 层是后端实打实的操作数据库，注解应见名知意，使用 @PostMapping 替代 @RequestMapping
    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
//        log.info("*******插入结果：" + result);

        if (result > 0){
            return new CommonResult(200, "插入数据库成功:), 端口号为：" + serverPort, result);
        }else {
            return new CommonResult(444, "插入数据库失败:(", result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
//        log.info("*******查询结果：" + payment  + "  嘻嘻嘻");

        if (payment != null){
            return new CommonResult(200, "查询成功:), 端口号为：" + serverPort, payment);
        }else {
            return new CommonResult(444, "没有对应记录:(，查询ID: " + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    //服务提供方 8001 提供故意暂停程序，以测试 openFeign 接口+注解 方式实现的 客户端调服务端方法会出现超时的情况
    //默认 Feign 服务端只等待 1s，这里睡了 3s，故会报错；yml 文件中可以自行设置超时时间
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }
}
