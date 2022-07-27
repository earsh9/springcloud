package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* 引入 Spring cloud Stream
* 对于不同的消息队列中间件，内部差异不同，调用其很费事
* 通过定义绑定器Binder作为中间层，实现了应用程序与消息中间件细节之间的隔离。而 Binder 是 Stream 中的核心部分
* 可以类比 jdbc 调用不同数据库
* input 为 消息消费者，output 为 消息生产者
* 本例中 8801 为消息驱动的生产者(发消息)，8802 为消费者(接收消息)
* */

@SpringBootApplication
public class StreamMQMain8801 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class, args);
    }
}
