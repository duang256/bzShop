package com.bjsxt.order.service.impl;

import com.bjsxt.mapper.TbOrderItemMapper;
import com.bjsxt.mapper.TbOrderMapper;
import com.bjsxt.mapper.TbOrderShippingMapper;
import com.bjsxt.order.feign.CommonRedisFeignClient;
import com.bjsxt.order.feign.FrontendCartFeignClient;
import com.bjsxt.order.service.OrderService;
import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单服务业务层
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private FrontendCartFeignClient frontendCartFeignClient;

    /**
     * 创建订单
     * @param tbOrderItem
     * @param tbOrder
     * @param tbOrderShipping
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping) {
        //获取订单ID,这里通过redis进行一个String的自增长，如果没有就从默认值开始
        Long orderId = this.commonRedisFeignClient.selectOrderId();

        //补齐TbOrder数据
        tbOrder.setOrderId(orderId.toString());
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        tbOrder.setStatus(1);
        Date date = new Date();
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);
        //0:未评价  1：已评价
        tbOrder.setBuyerRate(0);
        //将订单插入到数据库中
        int insert = this.tbOrderMapper.insert(tbOrder);


        int insert2 = 0;
        //插入订单中所包含的商品
        for(TbOrderItem item:tbOrderItem){
            item.setId(IDUtils.genItemId()+"");
            item.setOrderId(orderId.toString());
            insert2 += this.tbOrderItemMapper.insert(item);
            //将订单中的商品从购物车中删除，此处为复用
            this.frontendCartFeignClient.deleteItemFromCart(Long.parseLong(item.getItemId()), tbOrder.getUserId().toString());
        }

        //插入物流表数据
        tbOrderShipping.setOrderId(orderId.toString());
        tbOrderShipping.setUpdated(date);
        tbOrderShipping.setCreated(date);
        int insert3 = this.tbOrderShippingMapper.insert(tbOrderShipping);

        if(!(insert == 1 && insert2 == tbOrderItem.size() && insert3 == 1)){
            throw new RuntimeException();
        }
        return Result.ok(orderId);
    }
}
