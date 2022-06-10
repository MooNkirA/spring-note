package com.moon.springsample.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 测试 prototype 作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 11:12
 * @description
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 或 "prototype" 字符串
@Component
public class PrototypeScopeBean {

    @PostConstruct
    public void init() {
        System.out.println("PrototypeScopeBean postConstruct...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PrototypeScopeBean destroy...");
    }

}
