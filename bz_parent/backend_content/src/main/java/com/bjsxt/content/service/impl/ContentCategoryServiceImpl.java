package com.bjsxt.content.service.impl;

import com.bjsxt.content.feign.CommonContentFeignClient;
import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    /**
     * 查询内容分类
     * @param id
     * @return
     */
    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> list = this.commonContentFeignClient.selectContentCategoryByParentId(id);
        if(list != null && list.size() > 0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加内容分类
     * @param tbContentCategory
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertContentCategory(TbContentCategory tbContentCategory) {
        int contentCategoryNum = this.commonContentFeignClient.insertContentCategory(tbContentCategory);
        if(contentCategoryNum == 1){
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    /**
     * 删除内容分类
     * @param categoryId
     * @return
     */
    @Override
    @LcnTransaction
    public Result deleteContentCategoryById(Long categoryId) {
        int status = this.commonContentFeignClient.deleteContentCategoryById(categoryId);
        if(status == 1){
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    /**
     * 修改内容分类
     * @param tbContentCategory
     * @return
     */
    @Override
    @LcnTransaction
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        int num = this.commonContentFeignClient.updateContentCategory(tbContentCategory);
        if(num == 1){
            return Result.ok();
        }
        return Result.error("更新失败");
    }
}
