package com.bjsxt.order.fallback;

import com.bjsxt.order.feign.CommonRedisFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonRedisFeignClientFallbackFactory implements FallbackFactory<CommonRedisFeignClient> {
    @Override
    public CommonRedisFeignClient create(Throwable throwable) {
        return new CommonRedisFeignClient() {
            @Override
            public Long selectOrderId() {
                return null;
            }
        };
    }
}
