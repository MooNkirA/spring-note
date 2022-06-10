package com.moon.springsample.component;

/**
 * 测试 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 10:36
 * @description
 */
public class Component5 {

    public Component5() {
        System.out.println("Component5 加载到 Spring 容器中");
    }
    // 用于测试 @Bean 注解中指定的 initMethod 方法调用
    public void init() {
        System.out.println("Component5 的 init 方法执行了...");
    }

}
