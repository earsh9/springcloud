package springcloud.service.Impl;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import springcloud.service.IMessageProvider;

import javax.annotation.Resource;
import java.util.UUID;

/*
* 这里是去操作 MQ, 调用消息中间件的 service
* */

@EnableBinding(Source.class)            //定义消息的推送管道 (Source 为 Stream 驱动中的一个部件)
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;          //消息发送管道

    @Override
    public String Send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("********serial: " + serial);                    //作为消息的提供者 产生序列号 并发送给消息中间件即可
        return serial;
    }
}
