package com.bjsxt.backend.item.fallback;

import com.bjsxt.backend.item.feign.CommonRedisFeignClient;
import com.bjsxt.pojo.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonRedisFeignClientFallbackFactory implements FallbackFactory<CommonRedisFeignClient> {
    @Override
    public CommonRedisFeignClient create(Throwable throwable) {
        return new CommonRedisFeignClient() {
            @Override
            public void insertItemBasicInfo(TbItem tbItem) {

            }
            @Override
            public void deleteItemBasicInfoById(Long tbItemId) {

            }
            @Override
            public void insertItemDesc(TbItemDesc tbItemDesc) {

            }
            @Override
            public void deleteItemDescById(Long tbItemId) {

            }
            @Override
            public void insertItemParamItem(TbItemParamItem tbItemParamItem) {

            }
            @Override
            public void deleteItemParamItemById(Long tbItemId) {

            }
        };
    }
}
