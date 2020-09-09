package com.moon.springsample.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义用于多个切面类使用的 @Pointcut
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-8 23:47
 * @description
 */
public class MyPointcut {

    /*
     * 将一些公共的切入点表达式抽取到一个类中，使用 @Pointcut 注解修饰的方法设置为 public 即可(也可以根据不同的需求定义为`protected`或者空)
     *  同理，在某个切面类中，定义的@Pointcut方法，将包权限修饰符修改为 public，其他切面类也可以使用
     */
    @Pointcut("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    public void pt() {
    }

}
