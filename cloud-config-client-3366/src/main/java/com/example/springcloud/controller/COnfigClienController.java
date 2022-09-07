package com.example.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@Controller
public class COnfigClienController {

    @Value("${server.port}")
    String serverPort;

    @Value("${config.info}")
    String info;

    @RequestMapping("/dd")
    @ResponseBody
    public String configInfo(){
        return "serverPort:"+serverPort+"Info:"+info;
    }
}
