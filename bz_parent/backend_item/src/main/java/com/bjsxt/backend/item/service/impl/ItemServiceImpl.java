package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.feign.CommonRedisFeignClient;
import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;


    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = this.commonItemFeignClient.selectTbItemAllByPage(page, rows);
        if (pageResult != null && pageResult.getResult() != null && pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加TbItem，添加TbitemDesc，添加TbItemParamItem
     *
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        //补齐Tbitem数据
        Long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        tbItem.setUpdated(date);
        tbItem.setCreated(date);
        int tbItemNum = this.commonItemFeignClient.insertTbItem(tbItem);
        commonRedisFeignClient.insertItemBasicInfo(tbItem);

        //补齐商品描述对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int tbitemDescNum = this.commonItemFeignClient.insertItemDesc(tbItemDesc);
        commonRedisFeignClient.insertItemDesc(tbItemDesc);

        //补齐商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setCreated(date);
        Integer itemParamItmeNum = this.commonItemFeignClient.insertTbItemParamItem(tbItemParamItem);
        commonRedisFeignClient.insertItemParamItem(tbItemParamItem);

        if(!(tbItemNum == 1 && tbitemDescNum == 1 && itemParamItmeNum == 1)) {
           throw new RuntimeException();
        }

        return Result.ok();
    }

    /**
     * 删除商品
     *
     * @param itemId
     * @return
     */
    @Override
    @LcnTransaction
    public Result deleteItemById(Long itemId) {
        TbItem item = new TbItem();
        item.setId(itemId);
        item.setStatus((byte) 3);
        this.commonRedisFeignClient.deleteItemBasicInfoById(itemId);
        Integer itemNum = this.commonItemFeignClient.deleteItemById(item);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.commonRedisFeignClient.deleteItemBasicInfoById(itemId);
        if (itemNum == null) {
            throw new RuntimeException();
        }
        return Result.ok();
    }

    /**
     * 预更新商品查询
     *
     * @param itemId
     * @return
     */
    @Override
    public Result preUpdateItem(Long itemId) {
        Map<String, Object> map = this.commonItemFeignClient.preUpdateItem(itemId);
        if (map != null) {
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }

    /**
     * 更新商品：更新TbItem表，更新TbitemDesc表，更新TbItempParamItem表
     *
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    @LcnTransaction
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        //更新商品
        this.commonRedisFeignClient.deleteItemBasicInfoById(tbItem.getId());
        int itemNum = this.commonItemFeignClient.updateTbitem(tbItem);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.commonRedisFeignClient.deleteItemBasicInfoById(tbItem.getId());


        //更新商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        this.commonRedisFeignClient.deleteItemDescById(tbItem.getId());
        int itemDescNum = this.commonItemFeignClient.updateItemDesc(tbItemDesc);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.commonRedisFeignClient.deleteItemDescById(tbItem.getId());


        //更新商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setItemId(tbItem.getId());
        this.commonRedisFeignClient.deleteItemParamItemById(tbItem.getId());
        int itemParamItemNum = this.commonItemFeignClient.upateItemParamItem(tbItemParamItem);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.commonRedisFeignClient.deleteItemParamItemById(tbItem.getId());

        if (!(itemNum == 1 && itemDescNum == 1 && itemParamItemNum == 1)) {
            throw new RuntimeException();
        }
        return Result.ok();
    }

}
