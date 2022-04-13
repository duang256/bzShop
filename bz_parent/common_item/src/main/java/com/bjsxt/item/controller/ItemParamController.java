package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import io.protostuff.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ItemParam
 */
@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类ID查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){
        return this.itemParamService.selectItemParamByItemCatId(itemCatId);
    }


    /**
     * 查询所有商品规格参数模板
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam  Integer page,@RequestParam Integer rows){
        return this.itemParamService.selectItemParamAll(page,rows);
    }

    /**
     * 根据商品分类添加规格参数模板
     */

    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam){
        return this.itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 根据规格参数模板的ID删除数据
     */
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id){
        return this.itemParamService.deleteItemParamById(id);
    }
}
