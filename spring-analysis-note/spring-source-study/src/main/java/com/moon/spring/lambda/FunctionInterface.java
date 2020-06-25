package com.moon.spring.lambda;

/**
 * 所谓的函数式接口，当然首先是一个接口，
 * 然后就是在这个接口里面只能有一个抽象方法。
 * 这种类型的接口也称为SAM接口，即Single Abstract Method interfaces。
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-5-3 10:50
 * @description
 */
@FunctionalInterface
public interface FunctionInterface {

    /* 接口方法 */
    int doSomething(String s);


    /*
     * 函数式接口里允许定义默认方法
     */
    default void defaultMethod() {
        System.out.println("====我是函数式接口里的默认方法====");
    }

    /*
     * 函数式接口里允许定义java.lang.Object里的public方法
     */
    boolean equals(Object o);

    /*
     * 函数式接口里允许定义静态方法
     */
    static void staticMethod() {
        System.out.println("====我是函数式接口里的静态方法====");
    }

}
