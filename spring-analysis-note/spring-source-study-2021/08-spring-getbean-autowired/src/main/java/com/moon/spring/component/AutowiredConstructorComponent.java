package com.moon.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于测试构造函数使用 @Autowired 注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-24 17:27
 * @description
 */
@Component
public class AutowiredConstructorComponent {

    @Autowired
    public AutowiredConstructorComponent(ComponentA a, ComponentB b) {
        System.out.println(a);
        System.out.println(b);
    }

}
