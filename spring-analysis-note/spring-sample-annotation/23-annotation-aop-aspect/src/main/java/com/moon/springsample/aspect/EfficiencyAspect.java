package com.moon.springsample.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 用于统计方法执行效率的切面，用于测试多个切面情况时的执行顺序
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-8 16:49
 * @description
 */
@Component
/*
 * 多个切面类，同一个类型通知的执行顺序如下：
 *  1. 如果什么都配置的情况，是以类的名称首字母顺序来执行同一个类型的通知方法（与标识通知类型注解的方法名称无关）
 *  2. 通过在切面类上标识@Order注解来指定同一类型的通知方法的顺序，值越小越优先
 */
@Aspect
@Order(9)
public class EfficiencyAspect {

    private Long time;

    /**
     * 前置通知，记录开始时间
     */
    @Before("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    public void before() {
        time = System.currentTimeMillis();
        System.out.println("方法执行开始时间：" + time);
    }

    /**
     * 最终通知，统计方法执行时长
     */
    @After("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    public void after() {
        System.out.println("方法执行时间为:" + ((System.currentTimeMillis() - time) / 1000));
    }
}
