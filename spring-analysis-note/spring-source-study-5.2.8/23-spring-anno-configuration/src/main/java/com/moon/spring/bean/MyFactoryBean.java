package com.moon.spring.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean 实现测试素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-21 12:25
 * @description
 */
public class MyFactoryBean implements FactoryBean<Bird> {

    @Override
    public Bird getObject() throws Exception {
        return new Bird();
    }

    @Override
    public Class<?> getObjectType() {
        return Bird.class;
    }
}
