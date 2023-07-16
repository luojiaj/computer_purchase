package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
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
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder(){
        Order order = new Order();
        order.setUid(7);
        order.setRecvName("罗嘉杰");
        order.setRecvPhone("123456789");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000002);
        orderItem.setTitle("皮面日程本");
        orderMapper.insertOrderItem(orderItem);
    }

}
