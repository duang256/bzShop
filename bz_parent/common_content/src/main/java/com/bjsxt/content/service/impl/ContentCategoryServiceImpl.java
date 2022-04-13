package com.bjsxt.content.service.impl;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.mapper.TbContentCategoryMapper;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.pojo.TbContentCategoryExample;
import com.bjsxt.pojo.TbContentExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容分类业务层
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;


    /**
     * 根据父节点Id查询子节点
     *
     * @param parentId
     * @return
     */
    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        return list;
    }

    /**
     * 添加内容分类
     * 如果父节点不是父节点，修改父节点isParent为true
     * @param tbContentCategory
     * @return
     */
    @Override
    @LcnTransaction
    public int insertContentCategory(TbContentCategory tbContentCategory) {

        //查询名字是否重复，如果重复，添加失败
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andNameEqualTo(tbContentCategory.getName());
        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return 0;
        }


        //补齐数据
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        //插入数据
        Integer contentCategoryNum = this.tbContentCategoryMapper.insert(tbContentCategory);

        //查询当前新节点的父节点
        TbContentCategory contentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        //判断当前父节点是否是叶子节点
        if (!contentCategory.getIsParent()) {
            contentCategory.setIsParent(true);
            contentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        return contentCategoryNum;
    }

    /**
     * 删除内容分类
     * 同样要判断如果此节点的父节点没有其他子节点，需要将其isParent为false
     * @param categoryId
     * @return
     */
    @Override
    @LcnTransaction
    public int deleteContentCategoryById(Long categoryId) {

        //查询当前节点
        TbContentCategory currentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        //删除当前节点的子节点
        Integer status = this.deleteNode(currentCategory);
        //查询当前节点的父节点
        TbContentCategory parentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(currentCategory.getParentId());

        //查看当前节点是否有兄弟节点，决定是否修改父节点的状态。
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentCategory.getId());

        List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
        if (list.size() == 0) {
            parentCategory.setIsParent(false);
            parentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
        return status;
    }


    /**
     * 递归删除当前节点与子节点
     *
     */
    @LcnTransaction
    private int deleteNode(TbContentCategory currentCategory) {
        if (currentCategory.getIsParent()) {
            //是父节点

            //查询当前节点所有的子节点，删除子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(currentCategory.getId());
            List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
            for (TbContentCategory children : list) {
                this.deleteNode(children);
            }
            //删除父节点
            this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
        } else {
            //不是父节点,直接删除即可
            this.tbContentCategoryMapper.deleteByPrimaryKey(currentCategory.getId());
        }
        return 1;
    }

    /**
     * 修改内容分类名称
     *
     * @param tbContentCategory
     * @return
     */
    @Override
    @LcnTransaction
    public int updateContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setUpdated(new Date());
        return this.tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }

}
