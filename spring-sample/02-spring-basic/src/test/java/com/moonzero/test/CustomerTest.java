package com.moonzero.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moonzero.dao.ICustomerDao;
import com.moonzero.service.ICustomerService;

/**
 * 测试spring配置xml文件创建对象
 *
 * @author MoonZero
 */
public class CustomerTest {

    @Test
    public void test() {
        // 创建spring容器ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 根据bean的id获取对象
        ICustomerDao dao = (ICustomerDao) context.getBean("customerDao");
        ICustomerDao dao2 = (ICustomerDao) context.getBean("customerDao");
        System.out.println(dao);
        System.out.println(dao2);
        ICustomerService service = (ICustomerService) context.getBean("customerService");
        System.out.println(service);
    }

}
