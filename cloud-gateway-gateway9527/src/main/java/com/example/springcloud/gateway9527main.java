package com.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class gateway9527main {
    public static void main(String[] args) {
        SpringApplication.run(gateway9527main.class,args);
    }
}
