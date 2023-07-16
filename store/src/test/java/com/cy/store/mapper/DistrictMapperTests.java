package com.cy.store.mapper;

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
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for(District d:list){
            System.out.println(d);
        }
    }

    @Test
    public void findByNameCode(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
