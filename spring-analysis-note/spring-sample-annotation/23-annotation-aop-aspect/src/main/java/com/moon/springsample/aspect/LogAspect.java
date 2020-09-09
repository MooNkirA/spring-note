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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
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
// 标识当前类是一个切面类，如果不标识此注解，就算有@Compoent注解，也只能是让spring容器管理此类的实例，不会解析里面的相关通知方法
@Aspect
/*
 * 注意点1：当@Aspect注解中指定有值，或者切入点表达式后，此时当前类就会变成多例。如果不标识@Scope为多例，会报以下的错误
 *      Bean with name 'logAspect' is a singleton, but aspect instantiation model is not singleton
 * 注意点2：在@Aspect注解切入点表达式，其执行优先级会高于在@Pointcut注解或者在相关通知的注解上的表达式
 *
 * 实际开发中，在@Aspect注解中指定切入点表达式极少用
 */
// @Aspect("perthis(execution(* com.moon.springsample.sevice.impl.UserServiceImpl.saveAll(..)))")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 注意：通常情况下切面类是不需要多例的。
@Order(1) // 通过@Order注解来指定多个切面同一个类型的通知方法执行的顺序
public class LogAspect {

    /* @Before注解用于配置当前方法是一个前置通知 */
    @Before("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    public void beforeLog(JoinPoint joinPoint) {
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志");
    }

}
