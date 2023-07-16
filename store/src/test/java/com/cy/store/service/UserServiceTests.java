package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
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
public class UserServiceTests {
    //idea有检测的功能，接口是不能够直接创建Bean的对象(mybatis是动态代理技术),required=false即可解决
    @Autowired(required = false)
    private IUserService userService ;
    /**
     * 1.必须被@Test注解修饰
     * 2.返回值必须是void
     * 3.方法参数列表不指定任何类型
     * 4.方法访问修饰符必须是public
     */
    @Test
    public void reg(){
        try{
            User user = new User();
            user.setUsername("test002");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK,插入成功");
        } catch (ServiceException e){
            //获取类的对象再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            //异常具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = userService.login("test002","321");
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(6,"luojiajie","123","321");
    }

    @Test
    public void getUid(){
        System.out.println(userService.getByUid(7));
    }
    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("023232323213");
        user.setEmail("luojiaj88@outlookkk.com");
        user.setGender(0);
        userService.changeInfo(7,"管理员",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(7,"/upload/test.png","小明");
    }

}
