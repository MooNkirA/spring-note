package com.moon.spring.beanDefinition.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * InstantiationAwareBeanPostProcessor接口实现测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-9 13:59
 * @description
 */
// @Service
public class InstantiationAwareBeanPostProcessorDemo implements InstantiationAwareBeanPostProcessor {

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // 此postProcessAfterInstantiation方法，如果返回false，会导致Spring所有IOC失效
        return false;
    }

}
