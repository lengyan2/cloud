package com.example.springcloud.service.impl;

import com.example.springcloud.service.IMessageProvide;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@EnableBinding(Source.class)
public class MessageProvideImpl implements IMessageProvide {
    public static Map<String,Object> map = new HashMap<>();
    {
        for (int i = 0; i < 100; i++) {
            map.put(String.valueOf(i),"id="+i);
        }
    }
    @Resource
    MessageChannel output;

    @Override
    public String send() {
//        String serial = UUID.randomUUID().toString();

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry e:entries
             ) {
            MessageBuilder<Map.Entry> messageBuilder = MessageBuilder.withPayload(e);
            this.output.send(messageBuilder.build());
        }
//        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload(serial);
//        this.output.send(messageBuilder.build());
        return "success";
    }
}
