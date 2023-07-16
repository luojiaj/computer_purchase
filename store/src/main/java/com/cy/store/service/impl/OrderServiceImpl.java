package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.cartProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IAddressService addressService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ICartService cartService;


    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        //即将下单的列表
        List<cartProductVO> list = cartService.getVOByCid(uid, cids);
        //计算商品的总价
        Long totalPrice = 0L;
        for(cartProductVO c : list){
            //计算总价
            totalPrice += c.getRealPrice() * c.getNum();
        }
        Address address = addressService.findByAid(aid, uid);
        // 插入数据
        Order order = new Order();
        order.setUid(uid);
        //补全收货地址
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvAddress(address.getAddress());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        //是否支付，总价
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        //日志
        order.setCreatedTime(new Date());
        order.setCreatedUser(username);
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);
        Integer rows = orderMapper.insertOrder(order);
        if(rows != 1){
            throw new InsertException("订单数据插入时异常");
        }
        //创建订单详细项的数据,创建一个订单项对象
        for(cartProductVO c : list){
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getPrice());
            orderItem.setNum(c.getNum());
            //日志字段
            orderItem.setCreatedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            rows = orderMapper.insertOrderItem(orderItem);
            if(rows != 1){
                throw new InsertException("插入数据异常");
            }
        }
        return order;
    }
}
