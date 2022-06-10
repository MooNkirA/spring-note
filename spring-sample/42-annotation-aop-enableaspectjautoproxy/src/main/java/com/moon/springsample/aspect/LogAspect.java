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
     *   表达式意思是：匹配 任意返回值 com.moon.springsample.service.impl包下 任意类 任意方法 任意类型参数列表
     */
    // @Pointcut("execution(* com.moon.springsample.service.impl.*.*(..))")
    /*
     * 修改为切入点只包含某些方法，其他方法在调用该切入点方法时，也想被实现此增强的逻辑
     *  此时就需要通过暴露代理对象，再使用代理对象调用相应的切入点方法即可
     */
    @Pointcut("execution(* com.moon.springsample.service.impl.*.saveUser(..))")
    private void pt() {
    }

    /* @Before注解用于配置当前方法是一个前置通知 */
    @Before("pt()")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志");
    }

}
