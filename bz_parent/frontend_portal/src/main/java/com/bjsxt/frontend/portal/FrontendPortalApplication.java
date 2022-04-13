package com.bjsxt.frontend.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Frontend_portal服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FrontendPortalApplication {
    public static void main(String[] args){
        SpringApplication.run(FrontendPortalApplication.class,args);
    }
}
