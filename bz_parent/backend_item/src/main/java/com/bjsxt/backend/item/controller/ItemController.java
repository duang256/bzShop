package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backend/item")
public class ItemController {

    private Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;

    /**
     * 查询商品并分页处理
     * @return
     */
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "8") Integer rows){
        try{
            return this.itemService.selectTbItemAllByPage(page,rows);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }


    /**
     * 添加商品
     */
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem,String desc,String itemParams){
        try{
            return this.itemService.insertTbItem(tbItem,desc,itemParams);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 删除商品
     */
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId){
        try{
            return this.itemService.deleteItemById(itemId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 预更新商品查询
     */
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId){
        try{
            return this.itemService.preUpdateItem(itemId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 商品更新
     */
    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem,String desc,String itemParams){
        try{
            return this.itemService.updateTbItem(tbItem,desc,itemParams);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

}
