package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//@SpringBootTest表示标注当前的类是一个测试类，不会随同项目一块打包发送
@SpringBootTest
//@RunWith：表示启动这个单元测试类(不写的话是不能运行的，传递参数必须是SpringRunner实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    //idea有检测的功能，接口是不能够直接创建Bean的对象(mybatis是动态代理技术),required=false即可解决
    @Autowired(required = false)
    private UserMapper userMapper;
    /**
     * 1.必须被@Test注解修饰
     * 2.返回值必须是void
     * 3.方法参数列表不指定任何类型
     * 4.方法访问修饰符必须是public
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(6,"123","管理员",new Date());
        User user = userMapper.findByUsername("luojiajie");
        System.out.println(user);
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(6));
    }
    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(7);
        user.setPhone("07041450512");
        user.setEmail("test002@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(7,"/upload/avatar.png","管理员", new Date());
    }
}
