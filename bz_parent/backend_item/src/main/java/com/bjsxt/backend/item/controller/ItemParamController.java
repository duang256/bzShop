package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ItemParam
 */
@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    private Logger logger = LoggerFactory.getLogger(ItemParamController.class);
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类ID查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        try{
            return this.itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 查询商品全部规格参数
     */
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows){
        try{
            return this.itemParamService.selectItemParamAll(page,rows);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 添加商品分类规格参数模板
     */
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(Long itemCatId ,String paramData){
        try{
            return this.itemParamService.insertItemParam(itemCatId,paramData);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 删除规格参数模板
     */
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(Long id){
        try{
            return this.itemParamService.deleteItemParamById(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }
}
