package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemCategoryService;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.pojo.TbItemCatExample;
import com.bjsxt.utils.CatNode;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类查询
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;


    /**
     * 根据分类ID查询子节点
     * @param id
     * @return
     */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        return list;
    }

    /**
     * 查询首页商品分类
     * 此为递归查询所有分类
     * @return
     */
    @Override
    public CatResult selectItemCategoryAll() {
        CatResult catResult = new CatResult();
        //查询商品分类
        catResult.setData(getCatList(0L));
        System.out.println(catResult.getData().size());
        return catResult;
    }

    /**
     * 私有方法，查询商品分类
     */
    private List<?> getCatList(Long parentId){
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        int count = 0;
        for(TbItemCat tbItemCat:list){
             //是父节点，创建节点，并给自己节点内容递归查询
             if(tbItemCat.getIsParent()){
                 CatNode catNode = new CatNode();
                 catNode.setName(tbItemCat.getName());
                 //Item为List属性
                 catNode.setItem(getCatList(tbItemCat.getId()));
                 resultList.add(catNode);
                 count++;
                 //只取商品分类中的18条数据
                 if (count == 18){
                    break;
                 }
             }else{
                 //不是父节点，直接加入List
                resultList.add(tbItemCat.getName());
             }
         }
         return resultList;
    }
}
