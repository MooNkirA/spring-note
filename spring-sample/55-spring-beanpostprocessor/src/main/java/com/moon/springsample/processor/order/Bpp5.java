package com.moon.springsample.processor.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 使用 Ordered 接口排序
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 8:35
 * @description
 */
@Component
public class Bpp5 implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("使用 Ordered 接口 getOrder 方法返回 1 排序的 BeanPostProcessor 实现 Bpp5");
        return bean;
    }

    /**
     * 排序
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
