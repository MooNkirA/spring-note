package com.moon.springsample.test;

import com.moon.springsample.bean.Bean1;
import com.moon.springsample.bean.FactoryBeanDemo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * FactoryBean 接口测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-14 15:26
 * @description
 */
public class FactoryBeanTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample");
        // 实现了FactoryBean接口的类，通过bean的id只能获取该类实现了getObject()方法返回的对象实例
        System.out.println(context.getBean("myFactoryBean"));
        System.out.println(context.getBean("myFactoryBean")); // 测试多次获取是否为单例
        System.out.println(context.getBean("myFactoryBean")); // 测试多次获取
        // 测试若 getObjectType() 方法返回 null，在使用 getBean 方法根据类型获取 getObject 的实例时，会报错！
        System.out.println(context.getBean(Bean1.class));

        // 如果要获取实现了 FactoryBean 接口的类的实例，只能通过【"&" + beanName】来获取实例
        FactoryBeanDemo factoryBean = context.getBean("&myFactoryBean", FactoryBeanDemo.class);
        System.out.println(factoryBean);
    }

}
