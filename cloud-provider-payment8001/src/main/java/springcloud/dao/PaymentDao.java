package springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springcloud.entities.Payment;

/*
* Dao 接口为与数据库交互。使用 mybatis 的话最好使用 @Mapper 注解
* */

@Mapper
public interface PaymentDao {

    //只写基本的读和写操作
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
