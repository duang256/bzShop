package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    private Logger logger = LoggerFactory.getLogger(ContentCategoryController.class);


    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 商品分类菜单
     * 根据当前节点ID查询子节点
     * 仅仅返回下一层子节点，并非全部树结构
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        try{
            return this.contentCategoryService.selectContentCategoryByParentId(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 添加内容分类
     */
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(TbContentCategory tbContentCategory){
        try{
            return this.contentCategoryService.insertContentCategory(tbContentCategory);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 删除内容分类
     *
     */
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(Long categoryId){
        try{
            return this.contentCategoryService.deleteContentCategoryById(categoryId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 修改内容分类
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(TbContentCategory tbContentCategory){
        try{
            return this.contentCategoryService.updateContentCategory(tbContentCategory);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }
}
