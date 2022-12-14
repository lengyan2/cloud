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

        return "çº¿ç¨æ± ï¼" +Thread.currentThread().getName()+"paymentinfo_ok_id" + id+"ð";
    }

    @HystrixCommand(fallbackMethod = "PaymentInfo_timeoutHandler",
            commandProperties =
                    {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")}) // è¶æ¶éè¯¯
    public String paymentInfo_timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "çº¿ç¨æ± ï¼" +Thread.currentThread().getName()+"paymentinfo_tiemout_id" + id+"ð­";
    }


    public  String PaymentInfo_timeoutHandler(Integer id){
        return "çº¿ç¨æ± ï¼" +Thread.currentThread().getName()+"8001ç³»ç»åºépaymentinfo_tiemoutHandler" + id+"ðæå¡éçº§";
    }

    // æå¡çæ­ å½è¾¾å°ä¸å®çå¤±è´¥ç è¯·æ±ä¸å¨è¿è¡è°ç¨æå¡åä¼å¼å¯çæ­ï¼åé¨è®¾ç½®å§ç»ä¸ºå¹³åæéå¤çæ¶é´ï¼å½æå¼æ¶é¿è¾¾å°æ¶éè®¾ç½®æ¶é´ä¼è¿å¥åçæ­ç¶æ

    /**
     * çæ­å³é­ä¸ä¼å¯¹æå¡è¿è¡çæ­
     * çæ­åå¼ é¨åè¯·æ±æ ¹æ®è§åè°ç¨å½åæå¡ï¼å¦æè¯·æ±æååæå¡è§ååè®¤ä¸ºå½åæå¡æ¢å¤æ­£å¸¸ï¼å³é­çæ­
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // æ¯å¦å¼å¯çæ­å¨
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // è¯·æ±æ¬¡æ°
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // æ¶é´çªå£æ
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // å¤±è´¥çè¾¾å°å¤å°åè·³é¸
    })

    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("idä¸è½æ¯è´æ°");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"è°ç¨æåï¼æµæ°´å·:"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id ä¸è½ä¸ºè´æ°ï¼è¯·ç¨åéè¯ id+" +id ;
    }
}
