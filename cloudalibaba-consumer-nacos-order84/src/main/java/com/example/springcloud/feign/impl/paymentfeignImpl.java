package com.example.springcloud.feign.impl;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.feign.paymentfeign;
import org.springframework.stereotype.Component;

@Component
public class paymentfeignImpl implements paymentfeign {
    @Override
    public CommonResult payment(Long id) {
        return new CommonResult(444, " 服务降级返回 , 没有该流水信息 " , new Payment(id, "errorSerial......" ));
    }
}
