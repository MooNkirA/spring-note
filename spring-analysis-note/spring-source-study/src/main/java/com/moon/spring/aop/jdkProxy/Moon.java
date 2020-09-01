package com.moon.spring.aop.jdkProxy;

/**
 * 被代理类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-27 12:24
 * @description
 */
public class Moon implements People {

    @Override
    public String eat(String food) {
        System.out.println("Moon喜欢吃" + food);
        return food;
    }

}
