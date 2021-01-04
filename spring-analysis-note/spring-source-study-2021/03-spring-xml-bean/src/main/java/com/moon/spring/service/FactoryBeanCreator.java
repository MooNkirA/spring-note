package com.moon.spring.service;

import com.moon.spring.bean.FactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于测试 <bean> 标签的 factory-bean 属性与 factory-method 属性
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 17:11
 * @description
 */
public class FactoryBeanCreator {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryBeanCreator.class);

    public FactoryBean factoryMethod() {
        LOGGER.info("========= FactoryBeanCreator.factoryMethod() 方法执行了 =========");
        return new FactoryBean();
    }

}
