package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemParamItemService;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.pojo.TbItemParamItemExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    @LcnTransaction
    public int insertTbItemParamItem(TbItemParamItem tbItemParamItem) {
        return this.tbItemParamItemMapper.insert(tbItemParamItem);
    }

    @Override
    @LcnTransaction
    public int upateItemParamItem(TbItemParamItem tbItemParamItem) {
        tbItemParamItem.setUpdated(new Date());
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(tbItemParamItem.getItemId());
        return this.tbItemParamItemMapper.updateByExampleSelective(tbItemParamItem,example);
    }

    /**
     * 根据商品ID查询商品规格参数
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem selectTbItemParamItemByItemId(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        return list.get(0);
    }
}
