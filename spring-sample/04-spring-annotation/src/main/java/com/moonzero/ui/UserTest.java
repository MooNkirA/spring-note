package com.moonzero.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moonzero.service.IUserService;

/**
 * 模拟表现层
 *
 * @author MoonZero
 */
public class UserTest {
    public static void main(String[] args) {
        // 获取Spring容器对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 获取注解配置创建的对象
        IUserService us = (IUserService) context.getBean("userService");
        IUserService us2 = (IUserService) context.getBean("userService");
        // 调用对象方法
        us.addUser("注解对象");
        System.out.println(us == us2);
    }
}
