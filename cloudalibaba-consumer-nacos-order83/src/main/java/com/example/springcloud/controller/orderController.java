package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.feign.paymentFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class orderController {

    @Autowired
    paymentFeign paymentFeign;

    @GetMapping("order/payment/nacos/{id}")
    public CommonResult ordertest(@PathVariable("id") String id){
        String message = paymentFeign.nacosTest(id);
        CommonResult<String> stringCommonResult = new CommonResult<>();
        stringCommonResult.setCode(200);
        stringCommonResult.setMessage(message);
        return stringCommonResult;
    }
}
