package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单业务
 */
@RestController
@RequestMapping("/redis/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    /**
     * 生成订单ID
     */
    @RequestMapping("/selectOrderId")
    public Long selectOrderId(){
        return this.orderService.selectOrderId();
    }
}
