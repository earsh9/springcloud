package springcloudAlibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController
{
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")         //网址前缀不显示写进代码，使用调用配置文件使其分离
    private String serverURL;


    @GetMapping(value = "/consumer/payment/{id}")
    public String PaymentInfo(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(serverURL + "/payment/nacos/" + id, String.class);
    }
}
