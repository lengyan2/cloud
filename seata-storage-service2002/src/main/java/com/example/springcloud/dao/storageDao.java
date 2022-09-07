package com.example.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface storageDao {

    //减库存
    Integer reduceStorageByProduceidAnd_count(@Param("productId") Long productId,@Param("count") Integer count);
}
