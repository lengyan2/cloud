package com.example.springcloud.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class getwayconf1 {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){

        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("route_test_1",predicateSpec -> predicateSpec.path("/guonei").uri("http://news.baidu.com/guonei"));
        return routes.build();
    }


}
