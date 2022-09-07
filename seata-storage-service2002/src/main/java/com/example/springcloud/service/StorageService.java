package com.example.springcloud.service;

public interface StorageService {

    // 根据订单的商品id和数量减库存
    Integer reduce(Long produceId,Integer count);
}
