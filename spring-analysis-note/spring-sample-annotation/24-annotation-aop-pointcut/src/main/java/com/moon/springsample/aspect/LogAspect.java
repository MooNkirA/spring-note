package com.moon.springsample.aspect;

import com.moon.springsample.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 记录日志的切面
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:58
 * @description
 */
@Component
// 类上的@Aspect注解也可以指定抽取的公共切入点表达式，但指定后需要将作用范围设置为多例，否则会报错
@Aspect("perthis(com.moon.springsample.aspect.MyPointcut.pt())")
@Scope("prototype")
public class LogAspect {

    /*
     * @Pointcut切入点基础使用
     *   value属性：指定切入点表达式，在相应的通知类注解引入相应的方法名称即可
     */
    /*@Pointcut("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    private void pt() {
    }*/

    /* @Before注解用于配置当前方法是一个前置通知 */
    /*@Before("pt()")
    public void beforeLog() {
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志");
    }*/

    /*
     * @Pointcut切入点指定方法参数的使用
     *   在value属性的中使用了&&符号，表示并且的关系。
     *      &&符号后面的args和execution一样，都是切入点表达式支持的关键字，表示匹配参数。
     *      指定的内容可以是全限定类名，或者是名称。当指定参数名称时，要求与方法中形参名称相同
     *
     *   argNames属性，是定义参数的名称，该名称必须和args关键字中的名称一致。(其实不指定一样可以实现)
     */
    // 指定argNames属性，该名称必须和args关键字中的名称一致。
    // @Pointcut(value = "execution(* com.moon.springsample.sevice.impl.*.*(..)) && args(user)", argNames = "user")
    // 不指定argNames属性，args指定参数名称时，要求与方法中形参名称相同
    // @Pointcut(value = "execution(* com.moon.springsample.sevice.impl.*.*(..)) && args(user)")
    // execution中方法参数使用全限定类名
    /*@Pointcut(value = "execution(* com.moon.springsample.sevice.impl.*.*(com.moon.springsample.domain.User)) && args(user)")
    private void pt(User user) {
    }*/

    /* @Before等注解通知类注解，与@Pointcut一样，不指定argNames属性也是可以 */
    // @Before(value = "pt(user)", argNames = "user")
    /*@Before("pt(user)")
    public void beforeLog(User user) {
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志" + user.toString());
    }*/

    /* 使用MyPointcut类中定义的全局@Pointcut注解 */
    @Before("com.moon.springsample.aspect.MyPointcut.pt()")
    public void beforeLog() {
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志");
    }
}
