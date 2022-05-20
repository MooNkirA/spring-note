package com.moon.springsample.service;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-20 20:40
 * @description
 */
public class AgentClassLoaderDemo {

    public AgentClassLoaderDemo() {
        System.out.println("AgentClassLoaderDemo 的构造方法执行了...");
    }

    public void normalMethod() {
        System.out.println("AgentClassLoaderDemo 类的普通方法执行了...");
        normalMethod2();
    }

    public static void staticMethod() {
        System.out.println("AgentClassLoaderDemo 类的静态方法执行了...");
        staticMethod2();
    }

    public void normalMethod2() {
        System.out.println("AgentClassLoaderDemo 类的普通方法2执行了...");
    }

    public static void staticMethod2() {
        System.out.println("AgentClassLoaderDemo 类的静态方法2执行了...");
    }
}
