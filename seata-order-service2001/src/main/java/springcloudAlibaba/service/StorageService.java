package springcloudAlibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloudAlibaba.domain.CommonResult;

@RestController
@FeignClient(name = "seata-storage-service")           //要调用的库存微服务名称 (使用 Feign 注解)
public interface StorageService {

    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam(value = "productId") Long productId, @RequestParam(value = "count") Integer count);
}

