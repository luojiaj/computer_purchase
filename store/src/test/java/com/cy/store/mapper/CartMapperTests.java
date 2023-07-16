package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//@SpringBootTest表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//@RunWith：表示启动这个单元测试类(不写的话是不能运行的，传递参数必须是SpringRunner实例类型
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;
    @Test
    public void insert(){
        Cart cart = new Cart();
        cart.setUid(7);
        cart.setPid(10000004);
        cart.setPrice(1000L);
        cart.setNum(2);
        cartMapper.insert(cart);
    }
    @Test
    public void updateNumByCid(){
        cartMapper.updateNumByCid(1,4,"管理员",new Date());
    }
    @Test
    public void findByUidAndPid(){
        Cart cart = cartMapper.findByUidAndPid(7,10000004);
        Cart cart1 = cartMapper.findByUidAndPid(8,10000004);
        System.out.println(cart);
        System.out.println(cart1);
    }
    @Test
    public void findByUid(){
        System.out.println(cartMapper.findVOByUid(7));
    }

    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOBycid(){
        Integer[] cids = {1,2,3,5,6,7,8,9};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
