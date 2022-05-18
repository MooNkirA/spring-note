package com.moon.springsample.component;

import org.springframework.context.annotation.Bean;

/**
 * 测试 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 10:36
 * @description
 */
public class Component3 {

    public Component3() {
        System.out.println("Component3 加载到 Spring 容器中");
    }

}
