package com.moon.springsample.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-20 20:39
 * @description
 */
@Aspect // 注意此切面与常规使用 Spring Aop 不一样，此切面不需要依赖 Spring 管理
public class AgentClassLoaderAspect {

    @Before("execution(* com.moon.springsample.service.AgentClassLoaderDemo.*())")
    public void before() {
        System.out.println("基于 agent 类加载实现的 AOP 切面的前置增强");
    }

}
