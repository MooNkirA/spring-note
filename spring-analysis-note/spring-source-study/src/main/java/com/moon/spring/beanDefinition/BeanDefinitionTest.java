package com.moon.spring.beanDefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * BeanDefinition 创建测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-9 16:46
 * @description
 */
// PriorityOrdered（排序，优先级）接口是用于Spring创建同一类型的Bean时进行排序
@Component
public class BeanDefinitionTest implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    /**
     * 在spring容器加载的执行此方法，可以手动创建BeanDefinition对象并注册到spring容器中
     *
     * @param registry 这是Spring框架的BeanDefinition的注册器，此注册器可以获取所有spring容器管理的BeanDefinition对象
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 创建GenericBeanDefinition对象
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置需要实例化的类
        genericBeanDefinition.setBeanClass(BeanClass.class);

        // 如果需要实例化的类中属性赋值，需要获取MutablePropertyValues属性，赋值到此属性中
        MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue("userName", "实现BeanDefinitionRegistryPostProcessor接口，手动创建BeanDefinition对象并注册到spring容器中");

        // 将BeanDefinition对象注册到spring容器中，spring实例化对象，必须将beanName与BeanDefinition对象进行映射。（即添加到beanDefinitionMap属性中）
        registry.registerBeanDefinition("beanClass", genericBeanDefinition);

        /* ============ 自定义注解注册 ============ */
        // 1. 创建扫描器，将BeanDefinitionRegistry（注册器）传入
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        // 2. 将自定义注解添加注解过滤器中
        scanner.addIncludeFilter(new AnnotationTypeFilter(MoonService.class));
        // 3. 设置扫描的包路径
        scanner.scan("com.moon.spring");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public int getOrder() {
        // Spring根据此数值在创建Bean时，进行排序。数值越少越优先
        return 0;
    }
}
