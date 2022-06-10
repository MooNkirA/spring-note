package com.moon.springsample.bean;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 测试 singleton 作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 11:12
 * @description
 */
// 直接写 "singleton" 字符或者不写，默认作用范围也是单例
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Component
public class SingletonScopeBean {

    /* **************************************************
     * 在单例类中直接注入其他作用域对象，会出现作用域失效问题
     * 以下4种方式可以解决作用域失效问题
     * ***************************************************/
    @Lazy
    @Autowired
    private PrototypeScopeBean1 bean1;
    @Autowired
    private PrototypeScopeBean2 bean2;
    @Autowired
    private ObjectFactory<PrototypeScopeBean3> bean3;
    @Autowired
    private ApplicationContext bean4;

    public PrototypeScopeBean1 getBean1() {
        return bean1;
    }

    public PrototypeScopeBean2 getBean2() {
        return bean2;
    }

    public PrototypeScopeBean3 getBean3() {
        return bean3.getObject();
    }

    public PrototypeScopeBean4 getBean4() {
        return bean4.getBean(PrototypeScopeBean4.class);
    }

    @PostConstruct
    public void init() {
        System.out.println("SingletonScopeBean postConstruct...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("SingletonScopeBean destroy...");
    }

}
