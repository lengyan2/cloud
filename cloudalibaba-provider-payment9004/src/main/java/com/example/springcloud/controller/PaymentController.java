package com.example.springcloud.controller;

import com.example.springcloud.entities.CommonResult;
import com.example.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {


    @Value("${server.port}")
    String serverPort;

    public static Map<Long, Payment> map = new HashMap<>();
    static {
        map.put(1L,new Payment(1L,"sddwafwada"));
        map.put(2L,new Payment(2L,"sddwafwawdwada"));
        map.put(3L,new Payment(3L,"rwqeca"));
    }

    @GetMapping("payment/{id}")
    public CommonResult payment(@PathVariable("id") Long id){
        Payment payment = map.get(id);
        CommonResult<Payment> from_mysql_receive = new CommonResult<>(200, "from mysql receive serverport"+serverPort, payment);
        return from_mysql_receive;
    }
}
