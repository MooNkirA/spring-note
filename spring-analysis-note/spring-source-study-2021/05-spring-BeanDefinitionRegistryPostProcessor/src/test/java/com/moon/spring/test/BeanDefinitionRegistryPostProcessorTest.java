package com.moon.spring.test;

import com.moon.spring.bean.BeanToAdd;
import com.moon.spring.bean.BeanToDelete;
import com.moon.spring.bean.BeanToEdit;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanDefinitionRegistryPostProcessor 接口使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-13 23:00
 * @description
 */
public class BeanDefinitionRegistryPostProcessorTest {

    /* BeanDefinition 新增、修改、邮件测试 */
    @Test
    public void testBeanDefinitionCRUD() {
        // 读取spring类路径下的配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BeanToAdd beanToAdd = context.getBean("beanToAdd", BeanToAdd.class);
        System.out.println(beanToAdd);
        BeanToEdit beanToEdit = context.getBean("beanToEdit", BeanToEdit.class);
        System.out.println(beanToEdit);

        try {
            BeanToDelete beanToDelete = context.getBean("beanToDelete", BeanToDelete.class);
            System.out.println(beanToDelete);
        } catch (BeansException e) {
            System.out.println(e.getMessage());
        }
    }

    /* 自定义注解扫描测试 */
    @Test
    public void testCustomAnnotationScan() {
        // 读取spring类路径下的配置文件(xml文件中只配置了扫描 com.moon.spring 包)
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // 获取实例工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 输出工厂所有bean实例名称
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        /*
         * 输出结果节选如下：
         *   beanCustomComponent
         *   otherPackageBeanCustomAnnotation
         *   otherPackageBeanCustomComponent
         * 从结果总结：
         *  1. 通过实现BeanDefinitionRegistryPostProcessor接口中增加需要扫描的自定义注解，只在定义时设置的包扫描路径才生效，原xml配置的包扫描路径无法扫描到此自定义注解
         *  2. 设置扫描自定义注解的路径，也会扫描Spring原生@Component及其衍生注解
         *  3. 自定义注解继承了Spring原生@Component注解，作用的类也同样可以被扫描并注册到spring容器中
         */
    }

}
