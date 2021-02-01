package com.moon.spring.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * InstantiationAwareBeanPostProcessor接口实现测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-31 11:26
 * @description
 */
@Component
public class CustomInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    // 注入spring环境对象
    @Autowired
    private Environment environment;

    /**
     * 从分析源码可知，当此方法返回值为false时，就停止执行依赖注入的逻辑
     * 所以这里可以做一下业务逻辑，去实现是否DI注入的控制
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // 通过spring环境对象获取自定义配置文件数据
        String property = environment.getProperty("di.enable");

        // 此postProcessAfterInstantiation方法，如果返回false，会导致Spring所有IOC失效
        return StringUtils.hasText(property) && "true".equalsIgnoreCase(property);
    }

}
