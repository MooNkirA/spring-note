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
        System.out.println("CustomBean 构造方法执行了...");
    }

    public void init() {
        System.out.println("CustomBean 基于 @Bean 注解 initMethod 方式实现的初始化方法");
    }

    public void destory() {
        System.out.println("CustomBean 基于 @Bean 注解 destroyMethod 方式实现的销毁方法");
    }

}
