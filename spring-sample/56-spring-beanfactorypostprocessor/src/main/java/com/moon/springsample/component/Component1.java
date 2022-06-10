package com.moon.springsample.component;

import org.springframework.stereotype.Component;

/**
 * 测试 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 10:36
 * @description
 */
@Component
public class Component1 {

    public Component1() {
        System.out.println("Component1 加载到 Spring 容器中");
    }

}
