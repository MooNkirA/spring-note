package com.moon.spring.beanpostprocessor;

import com.moon.spring.aop.targetsource.CustomTargetSourceCreator;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 用于设置 AbstractAutoProxyCreator 类中的 customTargetSourceCreators 属性值
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-6 16:27
 * @description
 */
@Component
public class TargetSourceCreatorPostProcessor implements BeanPostProcessor, PriorityOrdered, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 判断是否为AOP注解入口类
        if (bean instanceof AnnotationAwareAspectJAutoProxyCreator) {
            AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator = (AnnotationAwareAspectJAutoProxyCreator) bean;
            // 设置自定义的 TargetSourceCreator 接口的实现
            CustomTargetSourceCreator customTargetSourceCreator = new CustomTargetSourceCreator();
            customTargetSourceCreator.setBeanFactory(this.beanFactory); // 设置beanFactory
            annotationAwareAspectJAutoProxyCreator.setCustomTargetSourceCreators(customTargetSourceCreator);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 69;
    }
}
