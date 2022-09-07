package com.example.springcloud.controller;

import com.example.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    String serverPort;

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
     String result =    paymentService.paymentInfo_ok(id);
     log.info("*****😊"+result);
     return  result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentinfo_timeout(@PathVariable("id") Integer id){
        String result =  paymentService.paymentInfo_timeout(id);
        log.info("*******😭"+result);
        return result;
    }

    // 服务熔断 controller
    @GetMapping("payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
       String result =  paymentService.paymentCircuitBreaker(id);
        log.info("0000result"+result);
        return result;
    }
}
