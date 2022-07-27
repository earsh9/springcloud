package springcloudAlibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("springcloudAlibaba.dao")
public class MyBatisConfig {
}
