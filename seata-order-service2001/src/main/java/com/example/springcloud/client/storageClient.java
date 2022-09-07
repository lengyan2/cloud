package com.example.springcloud.client;

import com.example.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("seata-storage-service")
public interface storageClient {
    @PostMapping("/storage/{produceId}/{count}")
    public CommonResult reduceStroge(@PathVariable("produceId") Long produceId,
                                              @PathVariable("count") Integer count);
}
