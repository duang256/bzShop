package com.bjsxt.frontend.portal.fallback;

import com.bjsxt.frontend.portal.feign.CommonContentFeignClient;
import com.bjsxt.pojo.TbContent;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonContentFeignClientFallbackFactory implements FallbackFactory<CommonContentFeignClient> {
    @Override
    public CommonContentFeignClient create(Throwable throwable) {
        return new CommonContentFeignClient() {
            @Override
            public List<TbContent> selectFrontendContentByAD() {
                return null;
            }
        };
    }
}
