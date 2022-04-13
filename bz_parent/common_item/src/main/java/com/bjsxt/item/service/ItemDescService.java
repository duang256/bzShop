package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemDesc;

public interface ItemDescService {
    int insertTbitemDesc(TbItemDesc tbItemDesc);
    int updateItemDesc(TbItemDesc tbItemDesc);
    TbItemDesc selectItemDescByItemId(Long itemId);
}
