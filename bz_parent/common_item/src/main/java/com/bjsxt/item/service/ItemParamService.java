package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemParamService {

    TbItemParam selectItemParamByItemCatId(Long itemCatId);
    PageResult selectItemParamAll(Integer page, Integer rows);
    int insertItemParam(TbItemParam tbItemParam);
    int deleteItemParamById(Long id);
}
