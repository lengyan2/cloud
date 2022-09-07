package com.example.springcloud.service.impl;

import com.example.springcloud.dao.accountDao;
import com.example.springcloud.service.accountService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@Slf4j
public class reduceAccountMoneyImpl implements accountService {
    @Autowired
    accountDao accountDao;

    @Override
    public Integer reduceAccountMoney(Long userId, BigDecimal money) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("=======扣金额中 userID"+userId+"money =="+money );
        Integer result =  accountDao.ReduceAccountMeney(userId,money);
       return result;
    }
}
