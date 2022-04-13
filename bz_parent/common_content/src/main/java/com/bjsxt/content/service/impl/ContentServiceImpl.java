package com.bjsxt.content.service.impl;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.mapper.TbContentMapper;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentExample;
import com.bjsxt.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Value("${frontend.AD}")
    private Long AD;

    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page,rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = this.tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        PageResult result = new PageResult();
        //第几页
        result.setPageIndex(page);
        //总共多少页
        result.setTotalPage(pageInfo.getTotal());
        //此页的数据
        result.setResult(pageInfo.getList());
        return result;
    }

    /**
     * 根据分类添加内容
     * @param tbContent
     * @return
     */
    @Override
    @LcnTransaction
    public int insertTbContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        return this.tbContentMapper.insertSelective(tbContent);
    }

    /**
     * 删除分类内容
     * @param id
     * @return
     */
    @Override
    @LcnTransaction
    public int deleteContentByIds(Long id) {
        return this.tbContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询首页大广告位
     * 通过软编码AD查询
     * @return
     */
    @Override
    public List<TbContent> selectFrontendContentByAD() {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(this.AD);
        return this.tbContentMapper.selectByExample(example);
    }

    /**
     * 主键查询
     * 用于内容删除时查询分类是否是大广告
     * @param id
     * @return
     */
    @Override
    public TbContent selectContentById(Long id) {
        return this.tbContentMapper.selectByPrimaryKey(id);
    }
}
