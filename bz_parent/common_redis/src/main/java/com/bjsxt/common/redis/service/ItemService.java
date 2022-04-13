package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemService {

    void insertItemBasicInfo(TbItem tbItem);
    TbItem selectItemBasicInfo(Long tbItemId);
    void deleteItemBasicInfoById(Long tbItemId);

    void insertItemDesc(TbItemDesc tbItemDesc);
    TbItemDesc selectItemDesc(Long tbItemId);
    void deleteItemDescById(Long tbItemId);

    void insertItemParamItem(TbItemParamItem tbItemParamItem);
    TbItemParamItem selectItemParamItem(Long tbItemId);
    void deleteItemParamItemById(Long tbItemId);
}
