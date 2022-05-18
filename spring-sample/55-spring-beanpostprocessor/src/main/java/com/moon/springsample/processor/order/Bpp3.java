package com.moon.springsample.processor.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 使用 PriorityOrdered 接口排序
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 8:35
 * @description
 */
@Component
public class Bpp3 implements BeanPostProcessor, PriorityOrdered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("使用 PriorityOrdered 接口 getOrder 方法返回 5 排序的 BeanPostProcessor 实现 Bpp3");
        return bean;
    }

    /**
     * 排序
     */
    @Override
    public int getOrder() {
        return 5;
    }
}
