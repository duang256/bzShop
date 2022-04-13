package com.bjsxt.backend.item.feign;

import com.bjsxt.backend.item.fallback.CommonRedisFeignClientFallbackFactory;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 请求common-redis服务
 */
@FeignClient(value = "common-redis",fallbackFactory = CommonRedisFeignClientFallbackFactory.class)
public interface CommonRedisFeignClient {
    //添加商品缓存
    @PostMapping("/redis/item/insertItemBasicInfo")
    void insertItemBasicInfo(@RequestBody TbItem tbItem);

    //删除商品缓存
    @PostMapping("/redis/item/deleteItemBasicInfoById")
    void deleteItemBasicInfoById(@RequestParam Long tbItemId);

    //添加商品详情缓存
    @PostMapping("/redis/item/insertItemDesc")
    void insertItemDesc(@RequestBody TbItemDesc tbItemDesc);
    //删除商品详情缓存
    @PostMapping("/redis/item/deleteItemDescById")
    void deleteItemDescById(@RequestParam Long tbItemId);

    //添加商品桂格参数缓存
    @PostMapping("/redis/item/insertItemParamItem")
    void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);
    //删除商品规格参数缓存
    @PostMapping("/redis/item/deleteItemParamItemById")
    void deleteItemParamItemById(@RequestParam Long tbItemId);
}
