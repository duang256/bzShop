package com.bjsxt.frontend.portal.fallback;

import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.CatResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommonRedisFeignClientFallbackFactory implements FallbackFactory<CommonRedisFeignClient> {
    @Override
    public CommonRedisFeignClient create(Throwable throwable) {
        return new CommonRedisFeignClient() {
            @Override
            public void insertItemCategory(CatResult catResult) {

            }

            @Override
            public CatResult selectItemCategory() {
                return null;
            }

            @Override
            public void insertContentAD(List<Map> list) {

            }

            @Override
            public List<Map> selectContentAD() {
                return null;
            }

            @Override
            public void insertItemBasicInfo(TbItem tbItem) {

            }

            @Override
            public TbItem selectItemBasicInfo(Long tbItemId) {
                return null;
            }

            @Override
            public void insertItemDesc(TbItemDesc tbItemDesc) {

            }

            @Override
            public TbItemDesc selectItemDesc(Long tbItemId) {
                return null;
            }

            @Override
            public void insertItemParamItem(TbItemParamItem tbItemParamItem) {

            }

            @Override
            public TbItemParamItem selectItemParamItem(Long tbItemId) {
                return null;
            }
        };
    }
}
