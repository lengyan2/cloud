package com.example.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.entities.CommonResult;

public class CustomResultHandler {
    public static CommonResult handleException(BlockException blockException){
        return new CommonResult(200,"自定义限流处理信息");
    }

}
