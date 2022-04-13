package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存商品信息
 */
@RestController
@RequestMapping("/redis/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 缓存商品基本信息
     */
    @RequestMapping("/insertItemBasicInfo")
    public void insertItemBasicInfo(@RequestBody TbItem tbItem) {
        this.itemService.insertItemBasicInfo(tbItem);
    }

    /**
     * 查询缓存中的商品基本信息
     */
    @RequestMapping("/selectItemBasicInfo")
    public TbItem selectItemBasicInfo(@RequestParam Long tbItemId) {
        return this.itemService.selectItemBasicInfo(tbItemId);
    }

    /**
     * 删除缓存中的商品基本信息
     */
    @RequestMapping("/deleteItemBasicInfoById")
    public void deleteItemBasicInfoById(@RequestParam Long tbItemId) {
        this.itemService.deleteItemBasicInfoById(tbItemId);
    }


    /**
     * 缓存商品介绍信息
     */
    @RequestMapping("/insertItemDesc")
    public void insertItemDesc(@RequestBody TbItemDesc tbItemDesc) {
        this.itemService.insertItemDesc(tbItemDesc);
    }

    /**
     * 查询缓存中的商品介绍
     */
    @RequestMapping("/selectItemDesc")
    public TbItemDesc selectItemDesc(@RequestParam Long tbItemId) {
        return this.itemService.selectItemDesc(tbItemId);
    }

    /**
     * 删除缓存中的商品介绍
     */
    @RequestMapping("/deleteItemDescById")
    public void deleteItemDescById(@RequestParam Long tbItemId) {
        this.itemService.deleteItemDescById(tbItemId);
    }





    /**
     * 缓存商品的规格参数
     */
    @RequestMapping("/insertItemParamItem")
    public void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem) {
        this.itemService.insertItemParamItem(tbItemParamItem);
    }

    /**
     * 查询缓存中的商品规格参数
     */
    @RequestMapping("/selectItemParamItem")
    public TbItemParamItem selectItemParamItem(@RequestParam Long tbItemId) {
        return this.itemService.selectItemParamItem(tbItemId);
    }

    /**
     * 删除缓存中的商品规格参数
     */
    @RequestMapping("/deleteItemParamItemById")
    public void deleteItemParamItemById(@RequestParam Long tbItemId) {
        this.itemService.deleteItemParamItemById(tbItemId);
    }
}
