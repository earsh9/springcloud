package springcloudAlibaba.service;

import org.springframework.stereotype.Component;
import springcloud.entities.CommonResult;
import springcloud.entities.Payment;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回,没有该流水信息",new Payment(id, "errorSerial......"));
    }
}
