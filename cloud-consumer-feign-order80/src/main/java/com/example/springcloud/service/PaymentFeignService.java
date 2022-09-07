package com.example.springcloud.service;

import com.example.springcloud.conf.ServiceFeignConfiguration;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
     CommonResult<Payment> getPaymentById( Long id);

    @GetMapping("payment/feign/timeout")
    public String paymentfeignTimeout();
}
