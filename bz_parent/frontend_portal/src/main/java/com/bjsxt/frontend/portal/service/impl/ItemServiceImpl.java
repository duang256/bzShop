package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonItemFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询商品业务层
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 查询商品基本信息
     *
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemInfo(Long itemId) {
        try {
            TbItem tbItem = this.commonRedisFeignClient.selectItemBasicInfo(itemId);
            if (tbItem != null) {
                return Result.ok(tbItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TbItem tbItem = this.commonItemFeignClient.selectItemInfo(itemId);
        try {
            if (tbItem != null) {
                this.commonRedisFeignClient.insertItemBasicInfo(tbItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbItem != null) {
            return Result.ok(tbItem);
        }
        return Result.error("查无结果");
    }

    /**
     * 查询商品介绍
     *
     * @param itemId
     * @return
     */
    @Override
    public Result selectItemDescByItemId(Long itemId) {
        try {
            TbItemDesc tbItemDesc = this.commonRedisFeignClient.selectItemDesc(itemId);
            if (tbItemDesc != null) {
                return Result.ok(tbItemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc tbItemDesc = this.commonItemFeignClient.selectItemDescByItemId(itemId);

        try {
            if (tbItemDesc != null) {
                this.commonRedisFeignClient.insertItemDesc(tbItemDesc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbItemDesc != null) {
            return Result.ok(tbItemDesc);
        }
        return Result.error("查无结果");
    }

    /**
     * 根据商品ID查询商品规格参数
     */
    @Override
    public Result selectTbItemParamItemByItemId(Long itemId) {
        try {
            TbItemParamItem tbItemParamItem = this.commonRedisFeignClient.selectItemParamItem(itemId);
            if (tbItemParamItem != null) {
                return Result.ok(tbItemParamItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemParamItem tbItemParamItem = this.commonItemFeignClient.selectTbItemParamItemByItemId(itemId);

        try {
            if (tbItemParamItem != null) {
                this.commonRedisFeignClient.insertItemParamItem(tbItemParamItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (tbItemParamItem != null) {
            return Result.ok(tbItemParamItem);
        }
        return Result.error("查无结果");
    }
}
