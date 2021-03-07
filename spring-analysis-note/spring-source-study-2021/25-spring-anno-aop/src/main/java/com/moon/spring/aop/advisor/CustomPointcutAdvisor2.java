package com.moon.spring.aop.advisor;

import com.moon.spring.aop.advice.CustomMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 自定义PointcutAdvisor接口实现，实现Ordered接口排序测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-28 09:37
 * @description
 */
// @Component
public class CustomPointcutAdvisor2 implements PointcutAdvisor, Ordered {

    @Override
    public Pointcut getPointcut() {
        return Pointcut.TRUE;
    }

    @Override
    public Advice getAdvice() {
        return new CustomMethodInterceptor("Ordered.0");
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
