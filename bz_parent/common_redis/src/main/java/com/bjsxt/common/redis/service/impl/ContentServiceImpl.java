package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 缓存首页大广告位业务层
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //存储大广告的redis key
    @Value("${frontend_ad_redis_key}")
    private String frontend_ad_redis_key;


    /**
     * 向缓存中添加首页大广告位
     * @param list
     */
    @Override
    public void insertContentAD(List<Map> list) {
        this.redisTemplate.opsForValue().set(this.frontend_ad_redis_key,list);
    }

    /**
     * 查询缓存中的首页大广告位
     * @return
     */
    @Override
    public List<Map> selectContentAD() {
        return (List<Map>) this.redisTemplate.opsForValue().get(this.frontend_ad_redis_key);
    }


    /**
     * 删除大广告缓存
     */
    @Override
    public void deleteContentAD() {
        this.redisTemplate.delete(this.frontend_ad_redis_key);
    }
}
