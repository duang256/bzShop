package com.bjsxt.cart.fallback;

import com.bjsxt.cart.feign.CommonRedisFeignClient;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.CartItem;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonRedisFeignClientFallbackFactory implements FallbackFactory<CommonRedisFeignClient> {
    @Override
    public CommonRedisFeignClient create(Throwable throwable) {
        return new CommonRedisFeignClient() {
            @Override
            public Map<String, CartItem> selectCartByUserId(String userId) {
                return null;
            }

            @Override
            public void insertCart(Map<String, Object> map) {

            }

            @Override
            public TbUser checkUserToken(String token) {
                return null;
            }
        };
    }
}
