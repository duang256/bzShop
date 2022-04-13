package com.bjsxt.content.feign;

import com.bjsxt.content.fallback.CommonContentFeignClientFallbackFactory;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "common-content",fallbackFactory = CommonContentFeignClientFallbackFactory.class)
public interface CommonContentFeignClient {

    //--------------------/service/contentCategory--------
    @PostMapping("/service/contentCategory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("parentId") Long parentId );

    @PostMapping("/service/contentCategory/insertContentCategory")
    int insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    @PostMapping("/service/contentCategory/deleteContentCategoryById")
    int deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @PostMapping("/service/contentCategory/updateContentCategory")
    int updateContentCategory(@RequestBody TbContentCategory tbContentCategory);

    //------------------------/service/content
    @PostMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("categoryId") Long categoryId);

    @PostMapping("/service/content/insertTbContent")
    int insertTbContent(@RequestBody TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    int deleteContentByIds(@RequestParam("id") Long id);

    @RequestMapping("/service/content/selectContentById")
    TbContent selectContentById(@RequestParam("id") Long id);
}
