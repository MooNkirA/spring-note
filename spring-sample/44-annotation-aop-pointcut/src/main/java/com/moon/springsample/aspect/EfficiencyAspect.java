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
@Aspect
public class EfficiencyAspect {

    private Long time;

    /**
     * 前置通知，记录开始时间
     */
    @Before("com.moon.springsample.aspect.MyPointcut.pt()")
    public void before() {
        time = System.currentTimeMillis();
        System.out.println("方法执行开始时间：" + time);
    }

    /**
     * 最终通知，统计方法执行时长
     */
    @After("com.moon.springsample.aspect.MyPointcut.pt()")
    public void after() {
        System.out.println("方法执行时间为:" + ((System.currentTimeMillis() - time) / 1000));
    }

}
