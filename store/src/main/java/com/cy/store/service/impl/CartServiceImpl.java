package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import com.cy.store.vo.cartProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

//新增收货地址的实现类
@Service
public class CartServiceImpl implements ICartService {
    /**
     * 购物车的业务层依赖与购物车的持久层和商品的持久层**/
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid,
                          Integer amount, String username) {
            // 查询当前要添加的这个购物车是否在表中存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result == null){ //这个商品从来没有被添加到购物车中,则进行新增操作
            //需要创建cart对象，先创建cart对象
            Cart cart = new Cart();
            //补全数据: 参数传递的数据
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //价格来自于商品中的数据
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            //补全日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            Integer rows = cartMapper.insert(cart);
            if(rows != 1){ //插入失败
                throw new InsertException("插入数据时产生未知的异常");
            }
        } else { //这个商品之前已经被添加过，则更新这条数据的num值即可
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(),num,username,date);
            if(rows != 1){
                throw new UpdateException("更新时产生未知的异常");
            }
        }
    }

    @Override
    public List<cartProductVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows != 1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车总量
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() - 1;
        if(num < 0){
            throw new CartNotFoundException("减少商品失败，商品数量已为0");
        }
        Integer rows = cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows != 1){
            throw new UpdateException("更新数据失败");
        }
        //返回新的购物车总量
        return num;
    }

    @Override
    public List<cartProductVO> getVOByCid(Integer uid, Integer[] cids) {
        List<cartProductVO> result = cartMapper.findVOByCid(cids);
        Iterator<cartProductVO> it = result.iterator();
        while (it.hasNext()){
            cartProductVO cartVO =  it.next();
            if(!cartVO.getUid().equals(uid)){ //当前数据不属于当前用户
                result.remove(cartVO); //从集合中移除此元素
            }
        }
        return result;
    }
}
