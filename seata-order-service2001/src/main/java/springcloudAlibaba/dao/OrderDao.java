package springcloudAlibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springcloudAlibaba.domain.Order;

@Mapper
public interface OrderDao {

    //1.新建订单
    void create(Order order);

    //2. 修改订单状态，从 0 改为 1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
