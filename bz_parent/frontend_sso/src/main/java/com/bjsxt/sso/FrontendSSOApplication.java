package com.bjsxt.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户注册于登录服务
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.bjsxt.mapper")
public class FrontendSSOApplication {
    public static void main(String[] args){
        SpringApplication.run(FrontendSSOApplication.class,args);
    }
}
