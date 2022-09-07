package com.example.springcloud.controller;

import com.example.springcloud.domain.CommonResult;
import com.example.springcloud.domain.Storage;
import com.example.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    StorageService service;

    // 减库存
    @PostMapping("/storage/{produceId}/{count}")
    public CommonResult reduceStroge(@PathVariable("produceId") Long produceId,
                                              @PathVariable("count") Integer count){
     Integer i =  service.reduce(produceId,count);
     return  new CommonResult(200,"库存已扣除");
    }
}
