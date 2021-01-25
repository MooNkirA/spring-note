package com.moon.spring.test;

import com.moon.spring.bean.Dog;
import com.moon.spring.bean.Fish;
import com.moon.spring.bean.Student;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring 注册与获取实例的测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 11:39
 * @description
 */
public class GetBeanTest {

    /* 测试使用xml配置注册bean实例到spring容器 */
    @Test
    public void testXmlRegisterBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
        Assert.assertNotNull(student);
    }

    /* 测试使用注解配置注册bean实例到spring容器 */
    @Test
    public void testAnnotationRegisterBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.moon.spring");
        Dog dog = context.getBean("dog", Dog.class);
        System.out.println(dog);
        Assert.assertNotNull(dog);
    }

    /* 注册bean到spring容器方式3: 手动通过BeanFactory接口方法注册 */
    @Test
    public void testBeanFactoryRegisterBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.moon.spring");
        // 获取bean实例工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 获取目前容器中所有实例的名称
        System.out.println("===== 手动注册Fish实例前 =====");
        for (String singletonName : beanFactory.getSingletonNames()) {
            System.out.println(singletonName);
        }

        // 实例工厂注册实例
        beanFactory.registerSingleton("fish", new Fish());
        // 获取目前容器中所有实例的名称
        System.out.println("===== 手动注册Fish实例后 =====");
        for (String singletonName : beanFactory.getSingletonNames()) {
            System.out.println(singletonName);
        }

        // 通过实例工厂删除实例
        ((DefaultListableBeanFactory) beanFactory).destroySingleton("dog");
        // 获取目前容器中所有实例的名称
        System.out.println("===== 删除容器中的Dog实例后 =====");
        for (String singletonName : beanFactory.getSingletonNames()) {
            System.out.println(singletonName);
        }
    }

}
