package springcloudAlibaba.service.impl;


import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springcloudAlibaba.dao.OrderDao;
import springcloudAlibaba.domain.Order;
import springcloudAlibaba.service.AccountService;
import springcloudAlibaba.service.OrderService;
import springcloudAlibaba.service.StorageService;


import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;


    /*
    * 下订单->减库存->扣余额->改(订单)状态
    * */
    @Override
    public void create(Order order) {
        log.info("-----> 开始新建订单");
        orderDao.create(order);

        log.info("-----> 订单微服务开始调用库存。做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("-----> 订单微服务调用库存，扣减结束");

        log.info("-----> 订单微服务调用账户，减余额");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("-----> 订单微服务调用账户，扣减余额结束");

        log.info("------> 开始修改订单状态");
        orderDao.update(order.getUserId(), order.getStatus());
        log.info("------> 修改订单状态结束");

        log.info("------> 下订单结束！:)");
    }
}
