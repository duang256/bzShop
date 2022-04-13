package com.bjsxt.cart.controller;

import com.bjsxt.cart.service.CookieCartService;
import com.bjsxt.cart.service.RedisCartService;
import com.bjsxt.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    private Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private RedisCartService redisCartService;

    /**
     * 将商品加入购物车
     */
    @RequestMapping("/addItem")
    public Result addItem(Long itemId, String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //在用户未登录的状态下
                return this.cookieCartService.addItem(itemId, num, request, response);
            } else {
                // 在用户已登录的状态
                return this.redisCartService.addItem(itemId, num, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 查看购物车
     */
    @RequestMapping("/showCart")
    public Result showCart(String userId, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //在用户未登录的状态下
                return this.cookieCartService.showCart(request, response);
            } else {
                // 在用户已登录的状态
                return this.redisCartService.showCart(userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }


    /**
     * 修改购物车中商品的数量
     */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(Long itemId, String userId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //在用户未登录的状态下
                return this.cookieCartService.updateItemNum(itemId, num, request, response);
            } else {
                // 在用户已登录的状态
                return this.redisCartService.updateItemNum(itemId, num, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     * 删除购物车中的商品
     */
    @RequestMapping("/deleteItemFromCart")
    public Result deleteItemFromCart(@RequestParam Long itemId, @RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //在用户未登录的状态下
                return this.cookieCartService.deleteItemFromCart(itemId, request, response);
            } else {
                // 在用户已登录的状态
                return this.redisCartService.deleteItemFromCart(itemId, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }

    /**
     *
     * @param ids 商品id
     * @param userId 用户id
     * @return
     */
    @RequestMapping("/goSettlement")
    public Result goSettlement(String[] ids, String userId) {
        try {
            return this.redisCartService.goSettlement(ids, userId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500, "error");
    }
}
