package com.moon.spring.aop.targetsource;

import com.moon.spring.service.LogService;
import com.moon.spring.service.impl.LogServiceImpl;
import org.springframework.aop.framework.autoproxy.target.AbstractBeanFactoryBasedTargetSourceCreator;
import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 自定义 TargetSourceCreator
 * <p>
 * 1. 可以直接实现TargetSourceCreator接口，重写getTargetSource方法
 * 2. 也可以继承AbstractBeanFactoryBasedTargetSourceCreator抽象类，该类也是实现了TargetSourceCreator接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-6 16:34
 * @description
 */
public class CustomTargetSourceCreator extends AbstractBeanFactoryBasedTargetSourceCreator {

    @Override
    protected AbstractBeanFactoryBasedTargetSource createBeanFactoryBasedTargetSource(Class<?> beanClass, String beanName) {
        if (getBeanFactory() instanceof ConfigurableListableBeanFactory) {
            // 判断是否为需要代理的类型
            if (beanClass.isAssignableFrom(LogServiceImpl.class)) {
                // 创建自定义的TargetSource
                return new CustomTargetSource();
            }
        }
        return null;
    }
}

