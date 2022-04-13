package com.bjsxt.sso.fallback;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.sso.feign.CommonRedisFeignClient;
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
            public void insertUser(TbUser tbUser, String userToken) {

            }
            @Override
            public void logOut(String userToken) {

            }

            @Override
            public void insertCart(Map<String, Object> map) {

            }

            @Override
            public Map<String, CartItem> selectCartByUserId(String userId) {
                return null;
            }
        };
    }
}
