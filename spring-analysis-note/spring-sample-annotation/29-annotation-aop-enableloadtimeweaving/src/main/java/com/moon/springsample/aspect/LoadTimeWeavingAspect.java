package com.moon.springsample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 切面类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-10 15:15
 * @description
 */
// 因为META-INF/aop.xml文件中指定当前的切面类，所以不需要标识@Component注解，因为此注解是运行期生成对象。现在使用@EnableLoadTimeWeaving是在编译期生成对象
// @Component
@Aspect
public class LoadTimeWeavingAspect {

    @Pointcut("execution(* com.moon.springsample.service.impl.*.*(..))")
    private void pt() {
    }

    /*
     * 增强的需求：
     *   统计方法的执行时间，在开发与测试环境下统计，生产环境不统计
     */
    @Around("pt()")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 创建spring框架的秒表对象
        StopWatch stopWatch = new StopWatch(this.getClass().getSimpleName());
        try {
            // 2. 执行记录
            stopWatch.start(joinPoint.getSignature().getName());
            // 3. 执行切入点方法
            return joinPoint.proceed();
        } finally {
            // 4. 停止计时
            stopWatch.stop();
            // 5. 输出计时结果
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
