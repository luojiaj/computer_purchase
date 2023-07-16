package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.cartProductVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    /**
     * 插入购物车数据
     * 如果购物车中没有该商品则添加该商品
      * @param cart 购物车数据
     * @return 收影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * 如果购物车中已经存在该商品则直接增加数量
     * @param cid 购物车数据id
     * @param num 更新数量
     * @param modifiedUser 修改着
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id和商品的id来查询购物车中的数据
     * @param uid
     * @param pid
     * @return
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    List<cartProductVO> findVOByUid(Integer uid);

    /**
     * 根据用户id来查询当前购物车这条数据是否存在
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);

    //前台把购物车中的勾选的商品cid传过来，后端返回对应的商品
    List<cartProductVO> findVOByCid(Integer[] cids);
}
