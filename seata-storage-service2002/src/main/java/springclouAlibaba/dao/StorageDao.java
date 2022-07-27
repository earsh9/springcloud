package springclouAlibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    /**
     * 扣减库存
     */
    void decrease(@Param(value = "productId") Long productId, @Param(value = "count") Integer count);
}


