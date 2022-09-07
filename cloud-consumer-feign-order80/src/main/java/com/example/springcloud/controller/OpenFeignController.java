package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OpenFeignController {

    @Autowired
    PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/feign/get/{id}")
    public CommonResult<Payment> getpayment(@PathVariable("id")Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("consumer/payment/feign/timeout")
    public String TimeoutTest(){
        // openfeign ribbon 客户端一般默认等待一秒钟
      return   paymentFeignService.paymentfeignTimeout();
    }
}
