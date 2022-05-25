package com.moon.springsample.service;

import com.moon.springsample.annotations.MyAnnotation;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-24 10:06
 * @description
 */
public class DemoServiceImpl implements DemoSerivce {

    @MyAnnotation
    @Override
    public void foo() {
        System.out.println("DemoServiceImpl foo() 方法执行...");
    }

    @Override
    public void bar() {
        System.out.println("DemoServiceImpl bar() 方法执行...");
    }

}
