package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.handler.CustomResultHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

@GetMapping("/byRourse")
@SentinelResource(value = "byRourse",blockHandler = "handleException")
    public CommonResult<String> byRourse(){

    return  new CommonResult(200,"按资源名称限流测试ok",new Payment(2020L,"serial2022"));
}
public CommonResult handleException(BlockException e){
    return new CommonResult(444,e.getLocalizedMessage()+"服务不可用");
}
@GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl",blockHandlerClass = CustomResultHandler.class,blockHandler = "handleException")
    public CommonResult byUrl(){
    return new CommonResult(200,"按url限流测试",new Payment(2020L,"serial2022"));
}
}
