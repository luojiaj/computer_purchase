package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//@RunWith：表示启动这个单元测试类(不写的话是不能运行的，传递参数必须是SpringRunner实例类型
@RunWith(SpringRunner.class)
public class CartServiceTests {

    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart(){
        cartService.addToCart(7,10000008,3,"管理员");
    }

}
