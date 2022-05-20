package com.moon.springsample.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 基于 ajc 编译器实现的 AOP 增强切面
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-20 18:14
 * @description
 */
@Aspect // 注意此切面与常规使用 Spring Aop 不一样，此切面不需要依赖 Spring 管理
public class AjcCompilerAspect {

    @Before("execution(* com.moon.springsample.service.AjcCompilerDemo.normalMethod())")
    public void beforeNormal() {
        System.out.println("基于 ajc 编译器实现的 AOP 切面对普通方法前置增强");
    }

    @Before("execution(* com.moon.springsample.service.AjcCompilerDemo.staticMethod())")
    public void beforeStatic() {
        System.out.println("基于 ajc 编译器实现的 AOP 切面对静态方法前置增强");
    }

    // TODO: 构造方法的切面暂时不知道如何写
    /*@Before("execution(* com.moon.springsample.service.AjcCompilerDemo())")
    public void before() {
        System.out.println("基于 ajc 编译器实现的 AOP 切面对构造方法前置增强");
    }*/

}
