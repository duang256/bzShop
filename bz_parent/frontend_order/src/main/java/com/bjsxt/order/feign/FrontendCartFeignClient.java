package com.bjsxt.order.feign;

import com.bjsxt.order.fallback.FrontendCartFeignClientFallbackFactory;
import com.bjsxt.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "frontend-cart",fallbackFactory = FrontendCartFeignClientFallbackFactory.class)
public interface FrontendCartFeignClient {

    @PostMapping("/cart/deleteItemFromCart")
    Result deleteItemFromCart(@RequestParam("itemId") Long itemId, @RequestParam("userId")  String userId);
}
