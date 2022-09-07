package com.example.springcloud.Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(Sink.class)
public class ReceiveMessageListener {

    @Value("${server.port}")
    String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info("");
        System.out.println("消费者2号-->接收到的消息："+message.getPayload()+"\t"+"port ="+serverPort);
    }
}