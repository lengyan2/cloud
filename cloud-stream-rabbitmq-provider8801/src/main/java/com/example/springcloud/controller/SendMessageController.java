package com.example.springcloud.controller;

import com.example.springcloud.service.IMessageProvide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class SendMessageController {

    @Autowired
    IMessageProvide iMessageProvide;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return iMessageProvide.send();
    }
}
