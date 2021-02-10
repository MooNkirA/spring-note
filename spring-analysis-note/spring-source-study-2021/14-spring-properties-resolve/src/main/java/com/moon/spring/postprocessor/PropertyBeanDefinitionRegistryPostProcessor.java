package com.moon.spring.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * xml属性注入方案2: 通过修改 BeanDefinition 的 MutablePropertyValues 实现表达式注入
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 15:11
 * @description
 */
@Component
public class PropertyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    // 注入spring的环境对象
    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 通过BeanDefinitionRegistry注册中心获取指定的BeanDefinition(或者全部BeanDefinition，逐个循环处理)
        MutablePropertyValues propertyValues = registry.getBeanDefinition("propertiesXmlBean").getPropertyValues();
        // 循环所有属性
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            TypedStringValue typedStringValue = (TypedStringValue) propertyValue.getValue();
            if (typedStringValue != null) {
                // 获取xml配置中的值，值为${xxx.xxx}，截取后为即为配置文件的key，通过环境对象获取相应的配置文件中的value
                String value = typedStringValue.getValue();
                Optional.ofNullable(value)
                        .ifPresent(v -> propertyValue.setConvertedValue(environment.getProperty(v.substring(2, v.length() - 1))));
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // do nothing
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
