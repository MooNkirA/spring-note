package com.moon.springsample.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor 实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 22:19
 * @description
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("MyBeanPostProcessor 类构造方法执行了");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("基于实现 BeanPostProcessor 接口 postProcessBeforeInitialization() 方法，" + beanName + "初始化之前调用");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("基于实现 BeanPostProcessor 接口 postProcessAfterInitialization() 方法，" + beanName + "初始化之后调用");
        return bean;
    }
}
