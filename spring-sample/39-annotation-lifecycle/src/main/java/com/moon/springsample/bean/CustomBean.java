package com.moon.springsample.bean;

/**
 * 基于 @Bean 注解方式实现初始化与销毁的方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 22:33
 * @description
 */
public class CustomBean {

    public CustomBean() {
        System.out.println("CustomBean构造方法执行了...");
    }

    public void init() {
        System.out.println("CustomBean基于@Bean注解initMethod方式实现的初始化方法");
    }

    public void destory() {
        System.out.println("CustomBean基于@Bean注解destroyMethod方式实现的销毁方法");
    }

}
