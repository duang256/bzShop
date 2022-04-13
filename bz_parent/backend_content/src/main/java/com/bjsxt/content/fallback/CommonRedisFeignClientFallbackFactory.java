package com.bjsxt.content.fallback;

import com.bjsxt.content.feign.CommonRedisFeignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
public class CommonRedisFeignClientFallbackFactory implements FallbackFactory<CommonRedisFeignClient> {

    @Override
    public CommonRedisFeignClient create(Throwable throwable) {
        return new CommonRedisFeignClient() {
            @Override
            public void deleteContentAD() {

            }
        };
    }
}
