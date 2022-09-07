package com.example.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){

        return "线程池：" +Thread.currentThread().getName()+"paymentinfo_ok_id" + id+"😄";
    }

    @HystrixCommand(fallbackMethod = "PaymentInfo_timeoutHandler",
            commandProperties =
                    {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")}) // 超时错误
    public String paymentInfo_timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" +Thread.currentThread().getName()+"paymentinfo_tiemout_id" + id+"😭";
    }


    public  String PaymentInfo_timeoutHandler(Integer id){
        return "线程池：" +Thread.currentThread().getName()+"8001系统出错paymentinfo_tiemoutHandler" + id+"🈚服务降级";
    }

    // 服务熔断 当达到一定的失败率 请求不在进行调用服务后会开启熔断，内部设置始终为平均故障处理时间，当打开时长达到时钟设置时间会进入半熔断状态

    /**
     * 熔断关闭不会对服务进行熔断
     * 熔断半开 部分请求根据规则调用当前服务，如果请求成功切服务规则则认为当前服务恢复正常，关闭熔断
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 是否开启熔断器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率达到多少后跳闸
    })

    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("id不能是负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号:"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后重试 id+" +id ;
    }
}
