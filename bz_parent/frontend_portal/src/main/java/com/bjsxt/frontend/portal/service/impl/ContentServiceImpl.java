package com.bjsxt.frontend.portal.service.impl;

import com.bjsxt.frontend.portal.feign.CommonContentFeignClient;
import com.bjsxt.frontend.portal.feign.CommonRedisFeignClient;
import com.bjsxt.frontend.portal.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    /**
     * 查询首页大广告位
     * @return
     */
    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存，如果缓存有，直接返回
        //这里需要try，catch，如果缓存出错数据库查询可以继续
        try{
            List<Map> list = this.commonRedisFeignClient.selectContentAD();
            if(list != null && list.size() > 0){
                System.out.println("调用缓存");
                Map map = list.get(0);
                System.out.println(map.get("href"));
                return Result.ok(list);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //查询数据库，并添加缓存
        System.out.println("调用数据库");
        List<TbContent> contentList = this.commonContentFeignClient.selectFrontendContentByAD();
        List<Map> result = new ArrayList<>();
        //模型转换
        for(TbContent content:contentList){
            System.out.println(content.getUrl());
            Map map = new HashMap();
            map.put("heightB",240);
            map.put("src",content.getPic());
            map.put("width",670);
            map.put("alt",content.getSubTitle());
            map.put("srcB",null);
            map.put("widthB",550);
            map.put("href",content.getUrl());
            map.put("height",240);
            result.add(map);
        }
        if(contentList != null && contentList.size() > 0){
            //将查询到数据添加到缓存中
            this.commonRedisFeignClient.insertContentAD(result);
            return Result.ok(contentList);
        }
        return Result.error("查无结果");
    }
}
