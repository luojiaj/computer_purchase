package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
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
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(23);
        address.setPhone("17858802974");
        address.setName("罗佳姐");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(23);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(7);
        for(Address a : list){
            System.out.println(a);
        }
    }

    @Test
    public void findByAid(){
        Address a = addressMapper.findByAid(7);
        System.out.println(a);
    }
    @Test
    public void updateNonDefault(){
        System.out.println(addressMapper.updateNonDefault(7));
    }
    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(7,"luojiajie",new Date());
    }
    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(7);
    }
    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(7));
    }

}
