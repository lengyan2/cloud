package com.example.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class paymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "PaymentHystrixService _ok  8001服务器宕机了";
    }

    @Override
    public String paymentinfo_timeout(Integer id) {
        return "PaymentHystrixService_timeout_8001服务器宕机了";
    }
}
