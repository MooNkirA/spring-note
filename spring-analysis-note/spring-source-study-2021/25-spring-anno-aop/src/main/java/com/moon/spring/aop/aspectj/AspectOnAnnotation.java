package com.moon.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 基于注解的方式的AOP使用
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-25 22:43
 * @description
 */
@Component
@Aspect // 声明此类是一个切面
public class AspectOnAnnotation {

    /*
     * @Pointcut注解标识定义切入点
     * execution(表达式)：表示拦截的位置（方法）
     *  表达式语法：execution([修饰符] 返回值类型 包名.类名.方法名(参数))
     */
    @Pointcut("execution(public * com.moon.spring.service.*.*(..))")
    public void pc1() {
    }

    // ****************************************************
    //                 测试切面排序专用 - start
    // ****************************************************
    // @Before("pc1()")
    public void Abefore13() {
        System.out.println("==============AspectOnAnnotation Abefore13=========");
    }

    // @Before("pc1()")
    public void before13() {
        System.out.println("==============AspectOnAnnotation before13=========");
    }

    // @Before("pc1()")
    public void before132() {
        System.out.println("==============AspectOnAnnotation before132=========");
    }

    @Before("pc1()")
    public void before1() {
        System.out.println("==============AspectOnAnnotation before1=========");
    }

    // @Before(value = "pc1()", argNames = "joinPoint")
    public void before2(JoinPoint joinPoint) {
        System.out.println("==============AspectOnAnnotation before2=========");
    }

    @After(value = "pc1()")
    public void AAfter() {
        System.out.println("==============AspectOnAnnotation AAfter=========");
    }
    // ****************************************************
    //                 测试切面排序专用 - end
    // ****************************************************

    /**
     * 环绕通知（增强）
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pc1()")
    public Object aroudAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==============AspectOnAnnotation类的 @Around环绕通知的前置通知=========");
        Object result = joinPoint.proceed();
        System.out.println("==============AspectOnAnnotation类的 @Around环绕通知的后置通知=========");
        return result;
    }

    /**
     * 返回值后置通知
     *
     * @param joinPoint
     * @param retVal
     */
    @AfterReturning(value = "pc1()", returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        System.out.println("==============AspectOnAnnotation类的 @AfterReturning后置通知，获取返回值 :: " + retVal);
    }

    @AfterThrowing(value = "pc1()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("==============AspectOnAnnotation类的 @AfterReturning异常通知，获取异常 :: " + e);
    }

}
