package com.moonzero.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moonzero.entity.Customer;
import com.moonzero.entity.User;

/**
 * 依赖注入测试
 */
public class CustomerTest {

    /**
     * 构造方法注入数据
     */
    @Test
    public void testConstructor() {
        // 创建spring容器ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        Customer customer = (Customer) context.getBean("customer");
        System.out.println(customer);
    }

    /**
     * set方法注入数据
     */
    @Test
    public void testProperty() {
        // 创建spring容器ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        Customer customer = (Customer) context.getBean("customer1");
        System.out.println(customer);
    }

    /**
     * p名称空间注入数据
     */
    @Test
    public void testP() {
        // 创建spring容器ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        Customer customer = (Customer) context.getBean("customer2");
        System.out.println(customer);
    }

    /**
     * 注入集合属性
     */
    @Test
    public void testOther() {
        // 创建spring容器ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取bean对象
        User user = (User) context.getBean("user");
        user.test();
    }

}
