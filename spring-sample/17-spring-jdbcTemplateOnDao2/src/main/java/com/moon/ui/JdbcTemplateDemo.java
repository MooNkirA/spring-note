package com.moon.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moon.dao.IAccountDao;
import com.moon.entity.Account;

public class JdbcTemplateDemo {
    public static void main(String[] args) {
        // 创建spring容器操作对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据id获取对象
        IAccountDao jt = (IAccountDao) context.getBean("accountDao");

        // 调用查询该方法
        System.out.println(jt.findById(1));
        System.out.println(jt.findByName("敌法师"));

        // 更新数据
        // jt.updateAccount(new Account(3, null, 123F));
    }
}