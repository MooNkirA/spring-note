package com.moon.springsample.test;

import com.moon.springsample.bean.ValueBean;
import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;

import java.lang.reflect.Field;

/**
 * 注解 @Value 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 11:07
 * @description
 */
public class SpringValueTest {
    // 1. 创建注解扫描的容器
    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

    // @Value 基础测试
    @Test
    public void valueBasicTest() {
        // 2. 获取配置类对象对象
        SpringConfiguration configuration = context.getBean("springConfiguration", SpringConfiguration.class);
        // 3. 输出使用@Value注入的相关属性值
        System.out.println(configuration.getColor());
        System.out.println(configuration.getName());
        System.out.println(configuration.getNumber());
    }

    // 模拟解析流程 - 测试读取系统变量
    @Test
    public void test1() throws NoSuchFieldException {
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        // 获取 @Value("${JAVA_HOME}") 注解的属性
        Field field = ValueBean.class.getDeclaredField("home");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dependencyDescriptor).toString();
        System.out.println(value);

        // 解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);
        System.out.println(value);
    }

    // 模拟解析流程 - 测试读取字符串值，然后类型转换
    @Test
    public void test2() throws NoSuchFieldException {
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        // 获取 @Value("18") 注解的属性
        Field field = ValueBean.class.getDeclaredField("age");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dependencyDescriptor).toString();
        System.out.println(value);

        // 解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);
        // 通过 TypeConverter 接口将字符串类型转成与类对象匹配的数值类型
        Object age = beanFactory.getTypeConverter()
                .convertIfNecessary(value, dependencyDescriptor.getDependencyType());
        System.out.println(age.getClass());
    }

    // 模拟解析流程 - 测试读取 spEl 表达式
    @Test
    public void test3() throws NoSuchFieldException {
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        // 获取  @Value("#{@springConfiguration}") 注解的属性
        Field field = ValueBean.class.getDeclaredField("config");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dependencyDescriptor).toString();
        System.out.println(value);

        // 先解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);

        // 再解析 #{} @xxx
        Object configValue = beanFactory.getBeanExpressionResolver().evaluate(value, new BeanExpressionContext(beanFactory, null));
        // 通过 TypeConverter 转成与类对象匹配的类型
        Object result = beanFactory.getTypeConverter()
                .convertIfNecessary(configValue, dependencyDescriptor.getDependencyType());
        System.out.println(result);
    }

    // 模拟解析流程 - 测试读取复杂的 spEl 表达式
    @Test
    public void test4() throws NoSuchFieldException {
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        // 获取  @Value("#{'hello, ' + '${JAVA_HOME}'}") 注解的属性
        Field field = ValueBean.class.getDeclaredField("value");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dependencyDescriptor).toString();
        System.out.println(value);

        // 先解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);
        System.out.println(value);

        // 再解析 #{} @xxx
        Object configValue = beanFactory.getBeanExpressionResolver().evaluate(value, new BeanExpressionContext(beanFactory, null));
        // 通过 TypeConverter 转成与类对象匹配的类型
        Object result = beanFactory.getTypeConverter()
                .convertIfNecessary(configValue, dependencyDescriptor.getDependencyType());
        System.out.println(result);
    }
}
