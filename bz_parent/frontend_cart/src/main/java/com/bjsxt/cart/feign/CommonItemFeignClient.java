package com.bjsxt.cart.feign;

import com.bjsxt.cart.fallback.CommonItemFeignClientFallbackFactory;
import com.bjsxt.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-item",fallbackFactory = CommonItemFeignClientFallbackFactory.class)
public interface CommonItemFeignClient {

    @PostMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);
}
