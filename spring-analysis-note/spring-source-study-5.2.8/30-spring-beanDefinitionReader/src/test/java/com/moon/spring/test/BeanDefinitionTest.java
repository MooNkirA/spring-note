package com.moon.spring.test;

import com.moon.spring.bean.AnnotatedBean;
import com.moon.spring.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanDefinition 读取器测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-10-06 16:43
 * @description
 */
public class BeanDefinitionTest {

    /**
     * 测试 AnnotatedBeanDefinitionReader
     */
    @Test
    public void testAnnotatedBeanDefinitionReader() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 通过spring上下文对象，创建注解的BeanDefinition读取器
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        // 可以直接把某个类转换为BeanDefinition，并且会解析该类上的注解。注解包括：
        // @Conditional、@Scope、@Lazy、@Primary、@DependsOn、@Role、@Description
        annotatedBeanDefinitionReader.register(AnnotatedBean.class);
        // 注：如果使用AnnotationConfigApplicationContext无参构造创建spring容器，在手动加入BeanDefinition后需要调用refresh()方法刷新容器。
        // context.refresh();

        BeanDefinition bd = context.getBeanDefinition("annotatedBean");
        System.out.println(bd);
        AnnotatedBean annotatedBean = context.getBean("annotatedBean", AnnotatedBean.class);
        System.out.println(annotatedBean);
    }

    @Test
    public void testXmlBeanDefinitionReader() {

    }

}
