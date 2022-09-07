package com.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZKController {

    public static final String URL = "http://cloud-provider-payment";

    @Autowired
    RestTemplate    restTemplate;

    @RequestMapping("/comsumer/payment/zk")
    public String paymentinfo(){
        String result = restTemplate.getForObject(URL + "/payment/zk", String.class);
        log.info("****消费者调用支付服务"+result);
        return result;
    }
}
