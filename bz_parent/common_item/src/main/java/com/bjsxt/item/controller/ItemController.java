package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    /**
     * 查询商品数据
     */
    @RequestMapping(value="/selectTbItemAllByPage",method = RequestMethod.GET)
    public PageResult selectTbItemAllByPage(@RequestParam Integer page,@RequestParam Integer rows){
        return this.itemService.selectTbItemAllByPage(page,rows);
    }

    /**
     * 商品的添加
     */
    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem){
    return this.itemService.insertTbItem(tbItem);
    }


    /**
     * 删除商品
     */
    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return this.itemService.updateItemById(tbItem);
    }

    /**
     * 根据商品ID查询商品，商品分类，商品描述，商品规格参数
     */
    @RequestMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(Long itemId){
        return this.itemService.preUpdateItem(itemId);
    }

    /**
     * 对商品表的更新操作
     *
     */
    @RequestMapping("/upateTbitem")
    public Integer updateTbitem(@RequestBody TbItem tbItem){
        return this.itemService.updateItemById(tbItem);
    }



    /**
     * 根据商品ID查询商品
     */
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(@RequestParam Long itemId){
        return this.itemService.selectItemInfo(itemId);
    }
}
