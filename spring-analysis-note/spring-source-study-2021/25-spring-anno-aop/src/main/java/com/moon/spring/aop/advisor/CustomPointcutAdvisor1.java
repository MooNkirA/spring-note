package com.moon.spring.aop.advisor;

import com.moon.spring.aop.advice.CustomMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 自定义PointcutAdvisor接口实现，标识@Order注解排序测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-28 09:37
 * @description
 */
@Component
@Order(2)
public class CustomPointcutAdvisor1 implements PointcutAdvisor {

    @Override
    public Pointcut getPointcut() {
        return Pointcut.TRUE;
    }

    @Override
    public Advice getAdvice() {
        return new CustomMethodInterceptor("@Order(2)");
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

}
