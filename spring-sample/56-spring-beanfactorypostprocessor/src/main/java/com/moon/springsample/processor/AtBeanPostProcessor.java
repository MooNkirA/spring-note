package com.moon.springsample.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * BeanFactoryPostProcessor 后置处理器模拟配置类中 @Bean 注解生成实例功能
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 14:39
 * @description
 */
public class AtBeanPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            // 创建 Spring 操作元数据的工具类 CachingMetadataReaderFactory
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            // 创建指定文件的元数据信息对象
            // TODO: 这里是写死特定配置类，应该是读取容器中所有配置，日后有时间优化
            MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/moon/springsample/config/SpringConfiguration.class"));
            // 从元数据信息对象中，获取所有标识了 @Bean 注解的方法
            Set<MethodMetadata> methods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());

            // 循环所有方法
            for (MethodMetadata method : methods) {
                // 创建 bean 定义构造器 BeanDefinitionBuilder
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
                // 设置通过工厂方法生成 BeanDefinition，注意不是手动定义 BeanDefinition，还需要指定相应的 @Bean 注解所在的配置类的名称
                // TODO: 这里是写死配置类名称，应该是动态读取日后有时间优化
                builder.setFactoryMethodOnBean(method.getMethodName(), "springConfiguration");
                // 设置方法形参自动注入，默认是 AutowireCapableBeanFactory.AUTOWIRE_NO 不自动注入
                builder.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);

                // 处理 @Bean 注解中配置的属性值，此示例以 initMethod 属性为例
                Optional.ofNullable(method.getAnnotationAttributes(Bean.class.getName()).get("initMethod"))
                        .ifPresent(initMethod -> builder.setInitMethodName(initMethod.toString()));

                // 生成 BeanDefinition
                AbstractBeanDefinition bd = builder.getBeanDefinition();
                // 注册 BeanDefinition
                registry.registerBeanDefinition(method.getMethodName(), bd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

}
