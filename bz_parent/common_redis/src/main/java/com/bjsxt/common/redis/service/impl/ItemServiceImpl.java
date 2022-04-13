package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 缓存商品业务层
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //商品基本信息redis key + ":" + 商品id
    @Value("${frontend_item_basic_info_key}")
    private String frontend_item_basic_info_key;

    //商品详情redis key + ":" + 商品id
    @Value("${frontend_item_desc_key}")
    private String frontend_item_desc_key;

    //商品规格参数信息redis key + ":" + 商品id
    @Value("${frontend_item_param_key}")
    private String frontend_item_param_key;

    /**
     * 缓存商品基本信息
     *
     * @param tbItem
     */
    @Override
    public void insertItemBasicInfo(TbItem tbItem) {
        this.redisTemplate.opsForValue().set(this.frontend_item_basic_info_key + ":" + tbItem.getId(), tbItem);
    }

    /**
     * 查询缓存中的商品基本信息
     */
    @Override
    public TbItem selectItemBasicInfo(Long tbItemId) {
        return (TbItem) this.redisTemplate.opsForValue().get(this.frontend_item_basic_info_key + ":" + tbItemId);
    }

    /**
     * 删除缓存中的商品基本信息
     * @param tbItemId
     */
    @Override
    public void deleteItemBasicInfoById(Long tbItemId) {
        this.redisTemplate.delete(this.frontend_item_basic_info_key + ":" + tbItemId);
    }

    /**
     * 缓存商品介绍信息
     */
    @Override
    public void insertItemDesc(TbItemDesc tbItemDesc) {
        this.redisTemplate.opsForValue().set(this.frontend_item_desc_key + ":" + tbItemDesc.getItemId(), tbItemDesc);
    }

    /**
     * 查询缓存中的商品介绍
     */
    @Override
    public TbItemDesc selectItemDesc(Long tbItemId) {
        return (TbItemDesc) this.redisTemplate.opsForValue().get(this.frontend_item_desc_key + ":" + tbItemId);
    }

    @Override
    public void deleteItemDescById(Long tbItemId) {
        this.redisTemplate.delete(this.frontend_item_desc_key + ":" + tbItemId);
    }

    /**
     * 缓存商品的规格参数
     */
    @Override
    public void insertItemParamItem(TbItemParamItem tbItemParamItem) {
        this.redisTemplate.opsForValue().set(this.frontend_item_param_key + ":" + tbItemParamItem.getItemId(), tbItemParamItem);
    }

    /**
     * 查询缓存中的商品规格参数
     */
    @Override
    public TbItemParamItem selectItemParamItem(Long tbItemId) {
        return (TbItemParamItem) this.redisTemplate.opsForValue().get(this.frontend_item_param_key + ":" + tbItemId);
    }

    @Override
    public void deleteItemParamItemById(Long tbItemId) {
        this.redisTemplate.delete(this.frontend_item_param_key + ":" + tbItemId);
    }
}
