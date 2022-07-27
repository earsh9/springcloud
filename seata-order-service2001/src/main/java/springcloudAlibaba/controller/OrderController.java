package springcloudAlibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloudAlibaba.domain.CommonResult;
import springcloudAlibaba.domain.Order;
import springcloudAlibaba.service.OrderService;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")                        //真实暴露给客户端(浏览器)的页面。只能是 get 请求。底层feign要更新订单状态等都得是 post 请求
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
