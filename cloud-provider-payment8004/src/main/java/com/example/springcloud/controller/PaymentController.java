package com.example.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    String serverPort;

    @RequestMapping("/payment/zk")
    public String paymentzk(){

        return "sprintcloud with zookeeper"+serverPort +"\t"+ UUID.randomUUID().toString();
    }
}
