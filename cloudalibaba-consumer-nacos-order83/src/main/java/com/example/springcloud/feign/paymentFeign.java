package com.example.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("nacos-payment-provider")
public interface paymentFeign {

    @GetMapping("/payment/nacos/{id}")
    public String nacosTest(@PathVariable("id") String id);
}
