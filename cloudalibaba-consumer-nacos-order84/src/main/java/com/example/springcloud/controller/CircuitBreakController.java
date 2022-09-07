package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.feign.paymentfeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircuitBreakController {

    public String URL = "http://nacos-payment-provider";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/comsumer/fallback/{id}")
    @SentinelResource(value = "fallback",blockHandler = "blockHandler",fallback = "handlerFallback",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult forObject = restTemplate.getForObject(URL + "/payment/" + id, CommonResult.class);
        if (id==4){
            throw new IllegalArgumentException("非法参数异常");

        }
        else if (forObject.getData()==null){
            throw new NullPointerException("该id没有对应记录 空指针异常");
        }
        return forObject;

    }
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id, null);
        return new CommonResult(444,"兜底异常handlerFallback"+e.getMessage(),payment);
    }
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException e){
        Payment payment = new Payment(id, null);
        return  new CommonResult(444,"blockHandler-sentinel 限流 , 无此流水 : blockException"+e.getMessage(),payment);
    }

    @Resource
    paymentfeign paymentfeign;
    @SentinelResource
    @GetMapping("/consumer/openfeign/{id}")
    public CommonResult openfeign(@PathVariable("id") Long id){
        CommonResult paymetn = paymentfeign.payment(id);
        return paymetn;
    }
}
