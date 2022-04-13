package com.bjsxt.cart.fallback;

import com.bjsxt.cart.feign.CommonItemFeignClient;
import com.bjsxt.pojo.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;



@Component
public class CommonItemFeignClientFallbackFactory implements FallbackFactory<CommonItemFeignClient> {
    @Override
    public CommonItemFeignClient create(Throwable throwable) {
        return new CommonItemFeignClient() {
            @Override
            public TbItem selectItemInfo(Long itemId) {
                return null;
            }
        };
    }
}
