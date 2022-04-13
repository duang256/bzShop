package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品类目Controller
 */
@RestController
@RequestMapping("/backend/itemCategory")
public class ItemCategoryController {
    private Logger logger = LoggerFactory.getLogger(ItemCategoryController.class);

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 根据类目ID查询当前类目的子节点
     */
    @RequestMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(value = "id",defaultValue = "0") Long id){
        try{
            return this.itemCategoryService.selectItemCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

}
