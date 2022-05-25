package com.moon.springsample.test;

import com.moon.springsample.annotations.MyAnnotation;
import com.moon.springsample.service.DemoClass1;
import com.moon.springsample.service.DemoServiceImpl;
import com.moon.springsample.service.TestService;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;

import java.lang.reflect.Method;

/**
 * Spring AOP Pointcut 切入点匹配测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-24 11:30
 * @description
 */
public class SpringAopPointcutTest {

    // 基于 AspectJ 表达式匹配
    @Test
    public void testAspectJExpressionMethod() throws NoSuchMethodException {
        // 创建切入点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 设置 AspectJ 表达式
        pointcut.setExpression("execution(* bar())");
        // 调用 AspectJExpressionPointcut 类中的 matches 判断指定类中方法是否与切入点匹配
        Class<?> clazz = DemoServiceImpl.class;
        System.out.println(pointcut.matches(clazz.getMethod("foo"), clazz));
        System.out.println(pointcut.matches(clazz.getMethod("bar"), clazz));
    }

    // 基于 AspectJ 表达式匹配
    @Test
    public void testAspectJExpressionAnnotation() throws NoSuchMethodException {
        // 创建切入点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 设置 AspectJ 表达式，匹配标识了指定注解的方法
        pointcut.setExpression("@annotation(com.moon.springsample.annotations.MyAnnotation)");
        // 调用 AspectJExpressionPointcut 类中的 matches 测试以下情况是否匹配
        // 情况1：测试普通类中标识了 @MyAnnotation 注解的 foo 方法是否匹配
        System.out.println(pointcut.matches(DemoServiceImpl.class.getMethod("foo"), DemoServiceImpl.class)); // 结果：true
        // 情况2：测试普通类中没有标识了 @MyAnnotation 注解的 bar 方法是否匹配
        System.out.println(pointcut.matches(DemoServiceImpl.class.getMethod("bar"), DemoServiceImpl.class)); // 结果：false
        // 情况3：测试普通类上标识了 @MyAnnotation 注解，类中的 foo 方法是否匹配
        System.out.println(pointcut.matches(TestService.class.getMethod("foo"), TestService.class)); // 结果：false
        // 情况4：测试接口上标识了 @MyAnnotation 注解，其实现类中的 foo 方法是否匹配
        System.out.println(pointcut.matches(DemoClass1.class.getMethod("foo"), DemoClass1.class)); // 结果：false
    }

    // 自定义切入点匹配规则
    @Test
    public void testStaticMethodMatcherPointcut() throws NoSuchMethodException {
        // 创建 StaticMethodMatcherPointcut 切入点，自定义匹配规则
        StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
            // 自定义匹配的规则
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 检查方法上是否加了 @MyAnnotation 注解
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(MyAnnotation.class)) {
                    return true;
                }
                // 查看类上或者接口上是否加了 @MyAnnotation 注解
                annotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                return annotations.isPresent(MyAnnotation.class);
            }
        };
        // 调用 StaticMethodMatcherPointcut 类中的 matches 测试以下情况是否匹配
        // 情况1：测试普通类中标识了 @MyAnnotation 注解的 foo 方法是否匹配
        System.out.println(pointcut.matches(DemoServiceImpl.class.getMethod("foo"), DemoServiceImpl.class)); // 结果：true
        // 情况2：测试普通类中没有标识了 @MyAnnotation 注解的 bar 方法是否匹配
        System.out.println(pointcut.matches(DemoServiceImpl.class.getMethod("bar"), DemoServiceImpl.class)); // 结果：false
        // 情况3：测试普通类上标识了 @MyAnnotation 注解，类中的 foo 方法是否匹配
        System.out.println(pointcut.matches(TestService.class.getMethod("foo"), TestService.class)); // 结果：true
        // 情况4：测试接口上标识了 @MyAnnotation 注解，其实现类中的 foo 方法是否匹配
        System.out.println(pointcut.matches(DemoClass1.class.getMethod("foo"), DemoClass1.class)); // 结果：true
    }

}
