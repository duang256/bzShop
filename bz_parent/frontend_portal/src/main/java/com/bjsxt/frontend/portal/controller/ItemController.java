package com.bjsxt.frontend.portal.controller;

import com.bjsxt.frontend.portal.service.ItemService;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询商品Controller
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemController {


    private Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    /**
     * 查询商品基本信息
     */
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId) {
        try {
            return this.itemService.selectItemInfo(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 查询商品介绍
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(Long itemId) {
        try {
            return this.itemService.selectItemDescByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 根据商品ID查询商品规格参数
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(Long itemId) {
        try {
            return this.itemService.selectTbItemParamItemByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }
}
