package com.moon.springsample.processor.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 使用 @Order 注解排序
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 8:35
 * @description
 */
@Component
@Order(1)
public class Bpp1 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("使用 @Order(1) 注解排序的 BeanPostProcessor 实现 Bpp1");
        return bean;
    }

}
