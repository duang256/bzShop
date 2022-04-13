package com.bjsxt.content.feign;

import com.bjsxt.content.fallback.CommonRedisFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 请求common-redis服务
 */
@FeignClient(value = "common-redis", fallbackFactory = CommonRedisFeignClientFallbackFactory.class)
public interface CommonRedisFeignClient {
    //删除大广告缓存
    @PostMapping("/redis/content/deleteContentAD")
    void deleteContentAD();

}
