package com.example.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
@Mapper
public interface accountDao {

    // 根据传入的userID和money账户结算 扣除订单金额
    Integer ReduceAccountMeney(@Param("userId") Long userId, @Param("money")BigDecimal money);
}
