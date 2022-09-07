package com.example.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String nacosTest(@PathVariable("id") String id){

        return "registy nacos +" +serverPort +"serverport" +"\t"+id ;
    }

}
