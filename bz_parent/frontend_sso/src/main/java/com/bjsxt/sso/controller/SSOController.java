package com.bjsxt.sso.controller;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.sso.service.SSOService;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注册于登录
 */
@RestController
@RequestMapping("/sso")
public class SSOController {

    private Logger logger = LoggerFactory.getLogger(SSOController.class);
    @Autowired
    private SSOService ssoService;

    /**
     * 对用户的注册信息(用户名与电话号码)做数据校验
     */
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue, @PathVariable Integer checkFlag) {
        try {
            return this.ssoService.checkUserInfo(checkValue, checkFlag);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 用户注册
     */
    @RequestMapping("/userRegister")
    public Result userRegister(TbUser user) {
        try {
            return this.ssoService.userRegister(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 用户登录
     */
    @RequestMapping("/userLogin")
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            return this.ssoService.userLogin(username, password, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 用户退出登录
     */
    @RequestMapping("/logOut")
    public Result logOut(String token) {
        try {
            return this.ssoService.logOut(token);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }
}
