package com.moon.springsample.aspect;

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
 * 记录日志的切面
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:58
 * @description
 */
// 将当前切面类注册到spring容器中
@Component
// 标识当前类是一个切面类
@Aspect
public class LogAspect {

    /*
     * 定义切入点表达式
     *   表达式意思是：匹配 任意返回值 com.moon.springsample.sevice.impl包下 任意类 任意方法 任意类型参数列表
     */
    @Pointcut("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    private void pt() {
    }

    /* @Before注解用于配置当前方法是一个前置通知 */
    @Before("pt()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志");
    }

    /* @AfterReturning注解用于配置当前方法是一个后置通知 */
    @AfterReturning("pt()")
    public void afterReturnLog() {
        System.out.println("后置通知(@AfterReturning)：执行切入点方法后...记录日志");
    }

    /* @AfterThrowing注解用于配置当前方法是一个异常通知 */
    @AfterThrowing("pt()")
    public void afterThrowingLog() {
        System.out.println("异常通知(@AfterThrowing)：执行切入点方法出现异常时...记录日志");
    }

    /* @After注解用于配置当前方法是一个最终通知 */
    @After("pt()")
    public void afterLog() {
        System.out.println("最终通知(@After)：执行切入点方法完成后（不管有无异常都会执行）...记录日志");
    }

    /* @Around注解用于配置当前方法是一个环绕通知 */
    @Around("pt()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) {
        // 定义返回值
        Object retValue = null;

        try {
            // 前置通知
            System.out.println("环绕通知(@Around)：执行切入点方法之前...记录日志");

            // 获取切入点方法执行所需的参数
            Object[] args = joinPoint.getArgs();
            // 执行切入点的方法
            retValue = joinPoint.proceed(args);

            // 后置通知
            System.out.println("环绕通知(@Around)：执行切入点方法之后...记录日志");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //异常通知
            System.out.println("环绕通知(@Around)：执行切入点方法产生异常后记录日志");
        } finally {
            //最终通知
            System.out.println("环绕通知(@Around)：无论切入点方法执行是否有异常都记录日志");
        }
        return retValue;
    }

}
