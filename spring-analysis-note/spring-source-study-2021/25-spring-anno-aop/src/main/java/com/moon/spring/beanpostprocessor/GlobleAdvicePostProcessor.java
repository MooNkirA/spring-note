package com.moon.spring.beanpostprocessor;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 通过 BeanPostProcessor 类型的接口，在AOP入口类实例化后设置全局拦截器interceptorNames数组
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-28 16:59
 * @description
 */
@Component
public class GlobleAdvicePostProcessor implements BeanPostProcessor, PriorityOrdered {

    @Override
    public int getOrder() {
        return 101;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 判断是否为AOP注解入口类
        if (bean instanceof AnnotationAwareAspectJAutoProxyCreator) {
            AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator = (AnnotationAwareAspectJAutoProxyCreator) bean;
            // 设置全局拦截器
            annotationAwareAspectJAutoProxyCreator.setInterceptorNames("globleAdvice");
        }
        return bean;
    }

}
