package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonItemFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品分类业务层
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;



    /**
     * 查询首页商品分类
     * @return
     */
    @Override
    public Result selectItemCategoryAll() {
        //查询缓存
        try{
            CatResult catResult = this.commonRedisFeignClient.selectItemCategory();
            //判断缓存中是否命中，判空、Data判空、DataSize判数量
            if(catResult != null && catResult.getData() != null && catResult.getData().size() > 0){
                return Result.ok(catResult);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //查询数据库
        CatResult catResult = this.commonItemFeignClient.selectItemCategoryAll();
        //添加到缓存
        try{
            if(catResult != null && catResult.getData() != null && catResult.getData().size() > 0){
               this.commonRedisFeignClient.insertItemCategory(catResult);
                return Result.ok(catResult);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.error("查无结果");
    }
}
