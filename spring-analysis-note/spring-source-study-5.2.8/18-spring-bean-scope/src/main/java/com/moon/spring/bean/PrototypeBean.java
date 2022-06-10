package com.moon.spring.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用于测试 Spring Bean 的多例情况与作用范围
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-13 11:31
 * @description
 */
@Component
// @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // Bean实例是单例，默认不需要设置
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Bean实例是多例
public class PrototypeBean {

    public PrototypeBean() {
        System.out.println("PrototypeBean类的无参构造函数执行了....");
    }

}
