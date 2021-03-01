package com.moon.spring.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-28 09:34
 * @description
 */
public class CustomMethodInterceptor implements MethodInterceptor {

    private String sign;

    public CustomMethodInterceptor(String sign) {
        this.sign = sign;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String s = String
                .format("CustomMethodInterceptors标识为[ %s ]的invoke()方法执行。当前拦截方法名：%s", this.sign, invocation.getMethod().getName());
        System.out.println(s);
        // 调用拦截的方法
        return invocation.proceed();
    }

}
