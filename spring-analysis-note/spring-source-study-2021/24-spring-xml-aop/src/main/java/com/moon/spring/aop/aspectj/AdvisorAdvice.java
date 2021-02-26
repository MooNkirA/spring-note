package com.moon.spring.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-26 08:56
 * @description
 */
public class AdvisorAdvice {

    /**
     * 前置通知 作用：在业务层执行核心方法之前执行此方法记录日志
     */
    public void beforeAdvice() {
        System.out.println("前置通知：正在记录日志。。。。。。");
    }

    /**
     * 后置通知 作用：在业务层执行核心方法之后执行此方法记录日志
     */
    public void afterReturnningAdvice() {
        System.out.println("后置通知：正在记录日志。。。。。。");
    }

    /**
     * 异常通知 作用：在业务层执行核心方法出现异常后执行此方法记录日志
     */
    public void afterThrowingAdvice() {
        System.out.println("异常通知：正在记录日志。。。。。。");
    }

    /**
     * 最终通知 作用：在业务层执行核心方法最终执行此方法记录日志
     */
    public void afterAdvice() {
        System.out.println("最终通知：正在记录日志。。。。。。");
    }

    /**
     * 环绕通知（增强）
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object aroudAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==============AdvisorAdvice 环绕通知aroudAdvice()的前置通知=========");
        Object result = joinPoint.proceed();
        System.out.println("==============AAdvisorAdvice 环绕通知aroudAdvice()的后置通知=========");
        return result;
    }
}
