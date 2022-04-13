package com.bjsxt.order.fallback;

import com.bjsxt.order.feign.FrontendCartFeignClient;
import com.bjsxt.utils.Result;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class FrontendCartFeignClientFallbackFactory implements FallbackFactory<FrontendCartFeignClient> {
    @Override
    public FrontendCartFeignClient create(Throwable throwable) {
        return new FrontendCartFeignClient() {
            @Override
            public Result deleteItemFromCart(Long itemId, String userId) {
                return null;
            }
        };
    }
}
