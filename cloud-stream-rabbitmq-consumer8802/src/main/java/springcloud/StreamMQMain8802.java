package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* 做两个消息消费者 8802、8803
* */
/*
* 当有多个消息消费者： group 属性对解决以下两个问题很有效
* 1. 是否要消息分组：
* 不同组是可以全面消费的(重复消费)，            （一个月饼(消息)不能重复发到两个部门(消息集群中的两个消费者)去）
  同一组内会发生竞争关系，只有其中一个可以消费。
  因为对于每一个消费者 给出的组流水号不同，即默认是不同组可以重复消费；若想避免，需自行配置
  2. 持久化
  两个消费者，一个分组一个不分组。重启后发生消息丢失的故障
  有分组属性的消费者即使 重启后，也能获得之前没有消费的信息
* */
@SpringBootApplication
public class StreamMQMain8802 {
    public static void main(String[] args)
    {
        SpringApplication.run(StreamMQMain8802.class,args);
    }
}
