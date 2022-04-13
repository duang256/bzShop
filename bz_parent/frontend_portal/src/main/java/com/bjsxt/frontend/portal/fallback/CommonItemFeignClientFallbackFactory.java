package com.bjsxt.frontend.portal.fallback;

import com.bjsxt.frontend.portal.feign.CommonItemFeignClient;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.CatResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;



@Component
public class CommonItemFeignClientFallbackFactory implements FallbackFactory<CommonItemFeignClient> {
    @Override
    public CommonItemFeignClient create(Throwable throwable) {
        return new CommonItemFeignClient() {
            @Override
            public CatResult selectItemCategoryAll() {
                return null;
            }

            @Override
            public TbItem selectItemInfo(Long itemId) {
                return null;
            }

            @Override
            public TbItemDesc selectItemDescByItemId(Long itemId) {
                return null;
            }

            @Override
            public TbItemParamItem selectTbItemParamItemByItemId(Long itemId) {
                return null;
            }
        };
    }
}
