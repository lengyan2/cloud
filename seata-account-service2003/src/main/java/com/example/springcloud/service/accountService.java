package com.example.springcloud.service;

import java.math.BigDecimal;

public interface accountService {
    // 根据用户id和金钱数扣除账户金额
    Integer reduceAccountMoney(Long userId, BigDecimal money);
}
