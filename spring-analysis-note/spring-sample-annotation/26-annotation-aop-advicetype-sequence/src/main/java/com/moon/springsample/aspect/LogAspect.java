package com.moon.springsample.aspect;

import com.moon.springsample.domain.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
@Aspect
public class LogAspect {

    /* 定义切入点 */
    @Pointcut("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    private void pt() {
    }

    /*
     * 同一个切面中，同一个类型的通知执行顺序：
     *  以下两个前置通知的执行顺序是 beforeLog_C --> beforeLog_a
     *  所以spring aop默认的执行顺序是根据方法名逐个字母的ascII码表的值大小排序，越小越优先
     */
    /* @Before前置通知 2 */
    /*@Before("pt()")
    public void beforeLog_a() {
        System.out.println("beforeLog_a前置通知方法执行了...");
    }*/

    /* @Before前置通知 1 */
    /*@Before("pt()")
    public void beforeLog_C() {
        System.out.println("beforeLog_C前置通知方法执行了...");
    }*/

    /* ************************** 方法重载的情况 ********************************* */
    /*
     * 同一个切面中，同一个类型的通知方法重载的情况执行顺序：
     *  比较的方法与非重载的方法一致，也是根据比较方法名 + 参数列表的逐个字母的ascII码表的值大小排序，越小越优先
     */
    @Before("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    public void beforeLog() {
        System.out.println("beforeLog()前置通知方法执行了...");
    }

    @Before("execution(* com.moon.springsample.sevice.impl.*.*(..)) && args(user)")
    public void beforeLog(User user) {
        System.out.println("beforeLog(User user)前置通知方法执行了...");
    }

}
