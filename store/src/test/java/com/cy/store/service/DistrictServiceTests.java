package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//@SpringBootTest表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//@RunWith：表示启动这个单元测试类(不写的话是不能运行的，传递参数必须是SpringRunner实例类型
@RunWith(SpringRunner.class)
public class DistrictServiceTests {

    @Autowired
    private IDistrictService districtService;
    @Test
    public void getParent(){
        //86代表中国，所有省的父代号都是86
        List<District> list = districtService.getByParent("86");
        for(District d : list){
            System.err.println(d);
        }
    }
}
