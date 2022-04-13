package com.bjsxt.content;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * CommonContent启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("com.bjsxt.mapper")
public class CommonCotnentApplication {
    public static void main(String[] args){
        SpringApplication.run(CommonCotnentApplication.class,args);
    }

}
