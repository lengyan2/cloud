package com.example.springcloud.client;

import com.example.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient("seata-account-service")
public interface accountClient {
    @PostMapping("/account/{userId}/{money}")
    public CommonResult reduceMoney(@PathVariable("userId") Long userId,
                                    @PathVariable("money") BigDecimal money);

}
