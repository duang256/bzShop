package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    private Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;

    /**
     * 根据分类ID查询内容
     * 开始第一页，page为1，默认为30条
     * PageHelper分页
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows,Long categoryId){
        try{
            return this.contentService.selectTbContentAllByCategoryId(page,rows,categoryId);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try{
            return this.contentService.insertTbContent(tbContent);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

    /**
     * 删除分类的内容
     * 不支持批量删除，只是前端传给的值叫做ids
     */
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
        try{
            return this.contentService.deleteContentByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }

}
