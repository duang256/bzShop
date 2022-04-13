package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存用户业务层
 */
@Service
public class SSOServiceImpl implements SSOService {

    //用户token redis key + ":" + token
    @Value("${user_session_redis_key}")
    private String user_session_redis_key;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void insertUser(TbUser tbUser, String userToken) {
        //将密码置空，处于安全角度考虑
        tbUser.setPassword("");
        this.redisTemplate.opsForValue().set(this.user_session_redis_key+":"+userToken,tbUser,1, TimeUnit.DAYS);
    }

    /**
     * 用户退出登录
     * @param userToken
     */
    @Override
    public void logOut(String userToken) {
        this.redisTemplate.delete(this.user_session_redis_key+":"+userToken);
    }

    /**
     * 根据用户token判断用户在redis中是否失效
     * @param token
     * @return
     */
    @Override
    public TbUser checkUserToken(String token) {
        return (TbUser) this.redisTemplate.opsForValue().get(this.user_session_redis_key+":"+token);
    }

}
