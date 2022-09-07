package com.example.springcloud.controller;

import com.example.springcloud.domain.CommonResult;
import com.example.springcloud.domain.Order;
import com.example.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class orderController {

    @Autowired
    OrderService orderService;

    @PostMapping("createOrder")
    public CommonResult createOrder(@RequestBody Order order){
        System.out.println("==============="+order);
        orderService.create(order);
        return new CommonResult(200,"订单创建成功",null);
    }
}
