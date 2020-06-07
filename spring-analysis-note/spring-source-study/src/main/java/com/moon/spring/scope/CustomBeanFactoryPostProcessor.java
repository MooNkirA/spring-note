package com.moon.spring.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 实现BeanFactoryPostProcessor接口，用于注册自定义bean作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-7 09:22
 * @description
 */
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    // 实现postProcessBeanFactory方法，注册自定义作用域
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope("MooNkirAScope", new CustomScope());
    }

}
