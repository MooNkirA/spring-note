package com.moon.springsample.processor;

import com.moon.springsample.bean.Cat;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义 BeanPostProcessor 实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 15:31
 * @description
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cat) {
            System.out.println("基于实现 BeanPostProcessor 接口 postProcessBeforeInitialization() 方法，" + beanName + "初始化之前调用");
            Cat cat = (Cat) bean;
            // 模拟功能增强，这里只是设置属性
            cat.setName("在 BeanPostProcessor 接口中设置的名称");
            cat.setAge(1);
            return cat;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cat) {
            System.out.println("基于实现 BeanPostProcessor 接口 postProcessAfterInitialization() 方法，" + beanName + "初始化之后调用");
            Cat cat = (Cat) bean;
            // 模拟功能增强，这里只是设置属性
            cat.setColor("pink");
            cat.setAge(cat.getAge() + 1);
            return cat;
        }
        return bean;
    }
}