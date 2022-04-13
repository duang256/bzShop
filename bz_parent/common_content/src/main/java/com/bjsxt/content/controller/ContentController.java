package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import io.protostuff.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据分类查询内容
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Integer page,@RequestParam Integer rows, @RequestParam Long categoryId){
        return this.contentService.selectTbContentAllByCategoryId(page,rows,categoryId);
    }

    /**
     *  根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public int insertTbContent(@RequestBody TbContent tbContent){
        return this.contentService.insertTbContent(tbContent);
    }

    /**
     * 删除分类下的内容
     */
    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long id){
        return this.contentService.deleteContentByIds(id);
    }

    /**
     * 查询首页大广告位
     */
    @RequestMapping("/selectFrontendContentByAD")
    public List<TbContent> selectFrontendContentByAD(){
        return this.contentService.selectFrontendContentByAD();
    }

    /**
     * 根据id查询cid
     */
    @RequestMapping("/selectContentById")
    public TbContent selectContentById(@RequestParam Long id){
        return this.contentService.selectContentById(id);
    }

}
