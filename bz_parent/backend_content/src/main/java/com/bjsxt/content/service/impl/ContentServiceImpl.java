package com.bjsxt.content.service.impl;

import com.bjsxt.content.feign.CommonContentFeignClient;
import com.bjsxt.content.feign.CommonRedisFeignClient;
import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Value("${frontend.AD}")
    private Long AD;

    /**
     * 根据分类ID查询分类内容
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    @Override
    public Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageResult pageResult = this.commonContentFeignClient.selectTbContentAllByCategoryId(page,rows,categoryId);
        if(pageResult != null && pageResult.getResult().size() > 0){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 根据分类添加内容
     * 如果是大广告内容，延时双删
     * @param tbContent
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertTbContent(TbContent tbContent) {
        if(tbContent.getCategoryId() == this.AD){
            try {
                Thread.sleep(500);
                commonRedisFeignClient.deleteContentAD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int num = this.commonContentFeignClient.insertTbContent(tbContent);
        if(num == 1){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    /**
     * 删除分类下的内容
     * 如果是大广告内容，延时双删
     * @param ids
     * @return
     */
    @Override
    @LcnTransaction
    public Result deleteContentByIds(Long ids) {
        //查询广告对应的分类
        TbContent tbContent = this.commonContentFeignClient.selectContentById(ids);
        if(tbContent != null && tbContent.getCategoryId() == this.AD){
            try {
                Thread.sleep(500);
                commonRedisFeignClient.deleteContentAD();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int num = this.commonContentFeignClient.deleteContentByIds(ids);
        if(num == 1){
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
