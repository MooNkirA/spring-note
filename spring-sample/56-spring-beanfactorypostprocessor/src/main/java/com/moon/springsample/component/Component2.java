package com.moon.springsample.component;

import org.springframework.stereotype.Service;

/**
 * 测试 bean
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-17 10:36
 * @description
 */
@Service
public class Component2 {

    public Component2() {
        System.out.println("Component2 加载到 Spring 容器中");
    }

}
