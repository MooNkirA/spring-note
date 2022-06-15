package com.moon.springsample.processor;

import com.moon.springsample.bean.Bean1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 测试 BeanPostProcessor
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-14 21:35
 * @description
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    // 使用 FactoryBean 方式创建实例，不会执行 BeanPostProcessor 的实例初始化前回调
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myFactoryBean") && bean instanceof Bean1) {
            System.out.println(beanName + " postProcessBeforeInitialization");
        }
        return bean;
    }

    // 使用 FactoryBean 方式创建实例，会执行 BeanPostProcessor 的初始化后回调
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myFactoryBean") && bean instanceof Bean1) {
            System.out.println(beanName + " postProcessAfterInitialization");
        }
        return bean;
    }
}
