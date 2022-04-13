package com.bjsxt.backend.item.feign;

import com.bjsxt.backend.item.fallback.CommonItemFeignClientFallbackFactory;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(value = "common-item",fallbackFactory = CommonItemFeignClientFallbackFactory.class)
public interface CommonItemFeignClient {

    //--------------------/Service/Item
    @GetMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @PostMapping("/service/item/insertTbItem")
    Integer insertTbItem(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/deleteItemById")
    Integer deleteItemById(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/preUpdateItem")
    Map<String,Object> preUpdateItem(@RequestParam("itemId")  Long itemId);

    @PostMapping("/service/item/upateTbitem")
    Integer updateTbitem(@RequestBody TbItem tbItem);

    //--------------------/Service/itemCategory
    @PostMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam("id") Long id);

    //---------------------/Service/itemParam----------------------
    @PostMapping("/service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    @PostMapping("/service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam("page")  Integer page,@RequestParam("rows") Integer rows);

    @RequestMapping("/service/itemParam/insertItemParam")
    Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    @RequestMapping("/service/itemParam/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam("id") Long id);

    //--------------------/Service/itemDesc
    @PostMapping("/service/itemDesc/insertItemDesc")
    Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @PostMapping("/service/itemDesc/updateItemDesc")
    Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc);

    //--------------------/Service/itemParamItem
    @PostMapping("/service/itemParamItem/insertTbItemParamItem")
    Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @PostMapping("/service/itemParamItem/upateItemParamItem")
    Integer upateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

}
