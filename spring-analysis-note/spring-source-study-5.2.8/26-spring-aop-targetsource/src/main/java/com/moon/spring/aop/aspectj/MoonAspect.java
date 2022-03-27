package com.moon.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 基于注解的方式的AOP切面
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-6 16:20
 * @description
 */
@Component
@Aspect // 声明此类是一个切面
public class MoonAspect {

    /* @Pointcut注解标识定义切入点 */
    @Pointcut("execution(public * com.moon.spring.service.*.*(..))")
    public void pc1() {
    }

    /**
     * 环绕通知（增强）
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pc1()")
    public Object aroudAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==============MoonAspect类的 @Around环绕通知的前置通知=========");
        Object result = joinPoint.proceed();
        System.out.println("==============MoonAspect类的 @Around环绕通知的后置通知=========");
        return result;
    }

}
