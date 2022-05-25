package com.moon.springsample.service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-24 13:44
 * @description
 */
public class DemoClass1 implements DemoInterface1 {

    @Override
    public void foo() {
        System.out.println("DemoClass1 foo() 方法执行了...");
    }

}
