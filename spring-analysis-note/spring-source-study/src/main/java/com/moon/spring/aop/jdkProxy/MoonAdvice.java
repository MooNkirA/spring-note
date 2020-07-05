package com.moon.spring.aop.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理类，代理Moon类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-27 12:26
 * @description
 */
public class MoonAdvice implements InvocationHandler {

    private People people;

    public MoonAdvice(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置增强
        before();

        // 被代理方法
        Object value = method.invoke(people, args);

        // 后置增强
        after();

        return value;
    }

    private void before() {
        System.out.println("===========吃东西前要洗手==========");
    }

    private void after() {
        System.out.println("===========吃完东西后要收拾=============");
    }

}
