package com.moon.springsample.test;

import com.moon.springsample.service.TestService;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * Spring AOP 功能与使用分析（包含编程式实现 AOP 示例）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-24 10:10
 * @description
 */
public class SpringAopAnalysisTest {

    // 编程式实现 AOP 测试
    @Test
    public void testAopApis() {
        // 1. 备好切入点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 设置切入点 execution 表达式
        pointcut.setExpression("execution(* foo())"); // 所有类中的 foo 方法

        // 2. 设置增强通知。注意：拦截接口是 org.aopalliance.intercept.MethodInterceptor
        MethodInterceptor advice = invocation -> {
            System.out.println("do something before...");
            Object result = invocation.proceed(); // 调用目标方法
            System.out.println("do something after...");
            return result;
        };

        // 3. 根据切入点和通知，创建切面 Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        /*
         * 创建代理，代理的类型分以下三种情况
         *  a. proxyTargetClass = false, 目标实现了接口, 用 jdk 实现
         *  b. proxyTargetClass = false,  目标没有实现接口, 用 cglib 实现
         *  c. proxyTargetClass = true, 总是使用 cglib 实现
         */
        // DemoSerivce target = new DemoServiceImpl();
        TestService target = new TestService();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target); // 设置目标对象
        factory.addAdvisor(advisor); // 设置切面
        // 如果想使用 jdk 代理，必须在 ProxyFactory 中设置目标的实现了接口
        factory.setInterfaces(target.getClass().getInterfaces());
        factory.setProxyTargetClass(false); // 设置是否使用 cglib 实现代理
        // DemoSerivce proxy = (DemoSerivce) factory.getProxy();
        TestService proxy = (TestService) factory.getProxy();

        System.out.println(proxy.getClass()); // 用于查询代理的类型
        // 通过代理执行方法
        proxy.foo();
        proxy.bar();
    }

}
