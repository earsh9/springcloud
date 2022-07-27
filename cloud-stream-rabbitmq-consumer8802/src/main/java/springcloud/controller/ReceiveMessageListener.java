package springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)          //消费者要去监听
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)             //监听队列，用于消费者的队列消息接收
    public void input(Message<String> message){
        System.out.println("消费者1号：-----> 收到的消息：" + message.getPayload() + "\t port: " + serverPort);        //与发送方的 withPayload 想对应
    }
}
