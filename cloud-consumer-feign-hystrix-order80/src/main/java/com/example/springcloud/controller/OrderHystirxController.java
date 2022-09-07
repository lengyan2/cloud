package com.example.springcloud.controller;

import com.example.springcloud.entities.Payment;
import com.example.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
// 全局通用的服务降级方法
//HystrixCommand指明了fallback会使用自定义的 否者会使用全局fallback
@DefaultProperties(defaultFallback = "payment_global_fallbackMethod")
public class OrderHystirxController {
    @Autowired
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String Hystrix_ok(@PathVariable Integer id){
        String result = paymentHystrixService.paymentInfo_ok(id);
        log.info("********ok👌"+result);
        return  result;
    }
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String Hystrix_timeout(@PathVariable Integer id){
        int i = 10/0;
        String result = paymentHystrixService.paymentinfo_timeout(id);
        log.info("********timeout"+result);
        return  result;
    }

    public String paymentTimeoutHandler(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请10s后重试😭";
    }

    // 全局fallback
    public String payment_global_fallbackMethod(){
        return "GLobl异常信息，请稍后重试";
    }
}
