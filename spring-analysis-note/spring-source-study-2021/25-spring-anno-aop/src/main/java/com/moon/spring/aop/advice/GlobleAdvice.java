package com.moon.spring.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * 全局增强Advice，实现MethodInterceptor接口，
 * 用于测试用于手动设置到 AbstractAutoProxyCreator类的 interceptorNames 数组中
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-28 16:54
 * @description
 */
@Component
public class GlobleAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("GlobleAdvice.invoke拦截方法执行了....");
        return invocation.proceed();
    }

}
