package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    String serverPort;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int count = paymentService.create(payment);
        log.info("*******插入结构"+count);
        if (count >0){
            return new CommonResult(200,"插入数据库成功,serverPort"+serverPort);
        }else {
            return new CommonResult(444,"插入数据库失败");
        }
    }

    // 根据id进行查询
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("根据id查询结果");
        if (payment!=null){
            return new CommonResult(200,"查询数据成功,serverPort"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询数据"+id+"数据失败",null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

 }
