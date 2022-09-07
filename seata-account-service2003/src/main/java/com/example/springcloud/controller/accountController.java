package com.example.springcloud.controller;

import com.example.springcloud.domain.CommonResult;
import com.example.springcloud.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class accountController {


    @Autowired
    accountService accountService;

    @PostMapping("/account/{userId}/{money}")
    public CommonResult reduceMoney(@PathVariable("userId") Long userId,
                                    @PathVariable("money")BigDecimal money){
        Integer integer = accountService.reduceAccountMoney(userId, money);
        if (integer>0){
        return new CommonResult(200,"扣除金额成功");
    }
        {
        return new CommonResult(444,"扣除金额失败");}
    }


}
