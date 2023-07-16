package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//@RunWith：表示启动这个单元测试类(不写的话是不能运行的，传递参数必须是SpringRunner实例类型
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;
    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("213123213213");
        address.setName("罗杰");
        addressService.addNewAddress(23,"管理员",address);
    }

    @Test
    public void setDefault(){
        addressService.SetDefault(5,7,"管理员");
    }

    @Test
    public void delete(){
        addressService.delete(6,7,"管理员");
    }
}
