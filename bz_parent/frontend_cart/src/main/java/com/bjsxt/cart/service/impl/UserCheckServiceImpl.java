package com.bjsxt.cart.service.impl;

import com.bjsxt.cart.feign.CommonRedisFeignClient;
import com.bjsxt.cart.service.UserCheckService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 根据用户token校验用户是否失效业务层
 */
@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public TbUser checkUserToken(String token) {
        return this.commonRedisFeignClient.checkUserToken(token);
    }
}
