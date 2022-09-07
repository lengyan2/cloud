package com.example.springcloud.feign;

import com.example.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.springcloud.feign.impl.paymentfeignImpl;
@FeignClient(value = "nacos-payment-provider",fallback = paymentfeignImpl.class)
public interface paymentfeign {
    @GetMapping("payment/{id}")
    public CommonResult payment(@PathVariable("id") Long id);
}
