package com.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class consulOrderController {

    private final static  String URL = "http://consul-provider-payment-8006";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String consulOrder(){
        String result =restTemplate.getForObject("http://consul-provider-payment/payment/consul",String.class);
        log.info("**consul ： 消费者 接收" + result);
        return result;
    }
}
