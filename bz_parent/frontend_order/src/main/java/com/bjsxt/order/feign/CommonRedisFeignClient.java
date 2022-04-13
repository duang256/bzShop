package com.bjsxt.order.feign;

import com.bjsxt.order.fallback.CommonRedisFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "common-redis",fallbackFactory = CommonRedisFeignClientFallbackFactory.class)
public interface CommonRedisFeignClient {

    @PostMapping("/redis/order/selectOrderId")
    Long selectOrderId();
}
