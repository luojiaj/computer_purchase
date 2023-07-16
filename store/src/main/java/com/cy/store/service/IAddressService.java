package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

//收货地址业务层接口
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户的某个地址为默认地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 表示修改执行的人
     */
    void SetDefault(Integer aid,Integer uid,String username);

    /**
     * 删除用户选中的数据收货地址
     * @param aid
     * @param uid
     * @param username
     */
    void delete(Integer aid, Integer uid, String username);

    Address findByAid(Integer aid, Integer uid);
}
