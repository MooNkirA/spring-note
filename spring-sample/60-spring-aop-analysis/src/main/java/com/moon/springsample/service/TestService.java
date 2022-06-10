package com.moon.springsample.service;

import com.moon.springsample.annotations.MyAnnotation;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-24 10:08
 * @description
 */
@MyAnnotation
public class TestService {

    public void foo() {
        System.out.println("无实现接口的 TestService foo() 方法执行...");
    }

    public void bar() {
        System.out.println("无实现接口的 TestService bar() 方法执行...");
    }

}
