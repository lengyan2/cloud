package com.example.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class consulPaymentController {

    @Value("${server.port}")
    String serverPort;

    @GetMapping("/payment/consul")
    public String consulPayment(){

        return "spring cloud consul + " +serverPort +"\t" + UUID.randomUUID().toString();
    }
}
