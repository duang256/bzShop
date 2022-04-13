package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface ItemService {

    PageResult selectTbItemAllByPage(Integer page,Integer rows);
    int insertTbItem(TbItem tbItem);
    int updateItemById(TbItem tbItem);
    Map<String,Object> preUpdateItem(Long itemId);
    TbItem selectItemInfo(Long itemId);
}
