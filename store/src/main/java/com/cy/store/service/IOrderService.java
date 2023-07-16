package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;

public interface IOrderService {
    /**
     *
     * @param aid
     * @param uid
     * @param username
     * @param cids 根据cid来创建订单
     * @return
     */
    Order create(Integer aid, Integer uid, String username, Integer[] cids);
}
