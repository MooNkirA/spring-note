package com.moon.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * 通过BeanDefinitionRegistry注册中心设置BeanClass占位符
 * <p>
 * 注：此案例主要是测试Spring是否能解析占位符的beanClass，
 * 需要将xml配置文件中的context:property-placeholder标签放开，或者在配置中手动创建 PropertySourcesPlaceholderConfigurer 类。
 * 此示例使用后者
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 15:11
 * @description
 */
@Component
public class BeanClassDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            // 使用工具类读取properties文件
            Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties", ClassUtils.getDefaultClassLoader());
            // 读取所有占位符
            String beanClassProperty = properties.getProperty("moon.beanClass");
            for (String beanClass : beanClassProperty.split(",")) {
                // 创建BeanDefinition对象
                BeanDefinition beanDefinition = new GenericBeanDefinition();
                // 设置占位符的beanClass
                beanDefinition.setBeanClassName(beanClass);
                // 注册BeanDefinition
                String beanName = BeanDefinitionReaderUtils.generateBeanName(beanDefinition, registry);
                registry.registerBeanDefinition(beanName, beanDefinition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }
}
