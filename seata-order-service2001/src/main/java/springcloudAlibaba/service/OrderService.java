package springcloudAlibaba.service;

import springcloudAlibaba.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
