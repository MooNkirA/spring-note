package com.moon.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于测试构造函数和使用 @Autowired 注解标识的构造函数
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-24 17:27
 * @description
 */
@Component
public class AutowiredConstructorComponent {

    /* ****************************************************
     *  情况1：单个个标识@Autowired注解的有参构造函数
     ******************************************************/
    /*
     * 用于测试标识`@Autowired`注解的有参构造函数实例化过程
     */
    /*@Autowired
    public AutowiredConstructorComponent(ComponentA a, ComponentB b) {
        System.out.println(a);
        System.out.println(b);
    }*/

    /* ****************************************************
     *  情况2：多个标识@Autowired注解的有参构造函数，默认spring处理时会抛出异常。
     *  通过分析源码可知，此时可以将@Autowired注解的required属性值改成false即可
     ******************************************************/
    /*
     * 用于测试标识`@Autowired`注解的有参构造函数实例化过程
     */
    @Autowired(required = false)
    public AutowiredConstructorComponent(ComponentA a, ComponentB b) {
        System.out.println(a);
        System.out.println(b);
    }

    /*
     * 用于多个测试标识`@Autowired`注解的有参构造函数的情况。
     */
    @Autowired(required = false)
    public AutowiredConstructorComponent(ComponentA a) {
        System.out.println(a);
    }

    /* ****************************************************
     *  情况3：单个无标识@Autowired注解的有参构造函数
     ******************************************************/
    /*public AutowiredConstructorComponent(ComponentA a, ComponentB b) {
        System.out.println(a);
        System.out.println(b);
    }*/

    /* ****************************************************
     *  情况4：多个无标识@Autowired注解的有参构造函数
     *  此时实例化会报错，但报错非spring框架的错误，而是会报实例化时找不到构造函数
     *  因为这种情况下，spring框架实例化会去找无参构造函数。如不让程序报错，增加无参构造函数即可
     ******************************************************/
    /*public AutowiredConstructorComponent(ComponentA a, ComponentB b) {
        System.out.println(a);
        System.out.println(b);
    }

    public AutowiredConstructorComponent(ComponentA a) {
        System.out.println(a);
    }*/

    /* ****************************************************
     *  情况5：无参构造函数（默认不写会有一个默认的）
     ******************************************************/

}
