package com.bjsxt.sso.service.impl;

import com.bjsxt.mapper.TbUserMapper;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.pojo.TbUserExample;
import com.bjsxt.sso.service.SSOService;
import com.bjsxt.utils.*;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.bjsxt.sso.feign.CommonRedisFeignClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用户注册与登录业务层
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;


    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    /**
     * 对用户的注册信息(用户名与电话号码)做数据校验
     */
    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example =new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if(checkFlag == 1){
            criteria.andUsernameEqualTo(checkValue);
        }else if(checkFlag == 2){
            criteria.andPhoneEqualTo(checkValue);
        }
        int rows = this.tbUserMapper.countByExample(example);
        if(rows > 0){
            return Result.error("数据不可用");
        }
        return Result.ok(checkValue);
    }

    /**
     * 用户注册
     */
    @Override
    @LcnTransaction
    public Result userRegister(TbUser user) {
        //将密码做加密处理。
        String pwd = MD5Utils.digest(user.getPassword());
        user.setPassword(pwd);
        //补齐数据
        user.setCreated(new Date());
        user.setUpdated(new Date());
        this.tbUserMapper.insert(user);
        return Result.ok();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result userLogin(String username, String password,HttpServletRequest request,HttpServletResponse response) {
        //根据用户名密码查询数据库
        TbUser tbUser = this.login(username,password);
        if(tbUser == null){
            return Result.error("用户名或密码有误，请重新输入");
        }
        //将用户添加到Redis中
        String userToken = UUID.randomUUID().toString();
        Integer flag = this.insertUserToRedis(tbUser,userToken);
        if(flag == 500){
            return Result.error("登录失败");
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",userToken);
        map.put("userid",tbUser.getId().toString());
        map.put("username",tbUser.getUsername());

        //将临时购物车中的商品同步到永久购物车中
        this.syncCart(tbUser.getId().toString(),request);
        //同步购物车成功后，需要将临时购物车中的商品删除掉
        this.deleteCookieCart(request,response);
        return Result.ok(map);
    }

    /**
     * 同步购物车
     */
    private void syncCart(String userId,HttpServletRequest request){
        //获取临时购物车
        Map<String,CartItem> cookieCart = this.getCart(request);
        //获取永久购物车
        Map<String,CartItem> redisCart = this.getCart(userId);
        //删除永久购物车中所包含临时购物车中的商品
        for(String key:cookieCart.keySet()){
            redisCart.remove(key);
        }
        //将同步后的购物车缓存到redis中
        redisCart.putAll(cookieCart);
        //将永久购物车重新缓存到redis中
        this.addCartToRedis(userId,redisCart);
    }
    /**
     * 获取临时购物车
     */
    private Map<String,CartItem> getCart(HttpServletRequest request){
        //临时购物车已存在
        String cartJson= CookieUtils.getCookieValue(request,this.cart_cookie_name,true);
        if(StringUtils.isBlank(cartJson)){
            //临时购物车不存在
            return new HashMap<String,CartItem>();
        }
        try{
            //如果含有临时购物车，那么需要做json转换
            Map<String,CartItem> map = JsonUtils.jsonToMap(cartJson,CartItem.class);
            return map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new HashMap<String,CartItem>();
    }
    /**
     * 获取永久购物车
     */
    private Map<String,CartItem> getCart(String userId){
        try{
            Map<String,CartItem> cart = this.commonRedisFeignClient.selectCartByUserId(userId);
            if(cart == null){
                cart = new HashMap<String,CartItem>();
            }
            return cart;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new HashMap<String,CartItem>();
    }

    /**
     * 将永久购物车重新缓存到redis中
     */
    private void addCartToRedis(String userId,Map<String,CartItem> cart){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("cart",cart);
        this.commonRedisFeignClient.insertCart(map);
    }

    /**
     * 用户登录业务处理
     */
    private TbUser login(String username,String password){
        String pwd = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(pwd);
        List<TbUser> list = this.tbUserMapper.selectByExample(example);
        if(list == null || list.size() <= 0){
            return null;
        }
        return list.get(0);
    }
    /**
     * 将用户添加到Redis中
     */
    private Integer insertUserToRedis(TbUser tbUser,String userToken){
        try{
            this.commonRedisFeignClient.insertUser(tbUser,userToken);
            return 200;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 500;
    }

    /**
     *删除临时购物车
     */
    private void deleteCookieCart(HttpServletRequest request,HttpServletResponse response){
        CookieUtils.deleteCookie(request,response,this.cart_cookie_name);
    }

    /**
     * 用户退出登录
     * @param token
     * @return
     */
    @Override
    public Result logOut(String token) {
        this.commonRedisFeignClient.logOut(token);
        return Result.ok();
    }
}
