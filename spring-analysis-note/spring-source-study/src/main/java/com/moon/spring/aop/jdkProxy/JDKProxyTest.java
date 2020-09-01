package com.moon.spring.aop.jdkProxy;

import java.lang.reflect.Proxy;

/**
 * JDK动态代理测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-27 12:22
 * @description
 */
public class JDKProxyTest {

    public static void main(String[] args) {
        // 创建代理
        People peopleProxy = (People) Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(),
                new Class[]{People.class}, new MoonAdvice(new Moon()));

        // 通过代理调用增加的方法
        String result = peopleProxy.eat("朱古力");
        System.out.println("增加后方法返回的结果：" + result);
    }

}
