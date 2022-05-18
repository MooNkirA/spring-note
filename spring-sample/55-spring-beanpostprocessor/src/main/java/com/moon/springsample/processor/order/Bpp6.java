package com.moon.springsample.processor.order;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 无指定排序
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 8:35
 * @description
 */
@Component
public class Bpp6 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("无指定排序的 BeanPostProcessor 实现 Bpp6");
        return bean;
    }

}
