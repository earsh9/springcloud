package springclouAlibaba.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public interface StorageService {

    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
