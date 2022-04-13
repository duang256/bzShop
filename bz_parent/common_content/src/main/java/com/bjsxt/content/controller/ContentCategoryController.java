package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ContentCategoryController
 */
@RestController
@RequestMapping("/service/contentCategory")
public class ContentCategoryController {


    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据父节点ID查询子节点
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long parentId ){
        return this.contentCategoryService.selectContentCategoryByParentId(parentId);
    }

    /**
     * 添加内容分类
     */
    @RequestMapping("/insertContentCategory")
    public int insertContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return this.contentCategoryService.insertContentCategory(tbContentCategory);
    }

    /**
     * 删除内容分类
     */
    @RequestMapping("/deleteContentCategoryById")
    public int deleteContentCategoryById(@RequestParam Long categoryId){
        return this.contentCategoryService.deleteContentCategoryById(categoryId);
    }

    /**
     * 修改内容分类名称
     */
    @RequestMapping("/updateContentCategory")
    public int updateContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return this.contentCategoryService.updateContentCategory(tbContentCategory);
    }
}
