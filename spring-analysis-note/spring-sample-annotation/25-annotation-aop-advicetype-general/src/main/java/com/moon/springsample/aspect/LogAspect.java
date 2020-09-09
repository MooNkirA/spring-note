package com.moon.springsample.aspect;

import com.moon.springsample.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 记录日志的切面
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:58
 * @description
 */
// 将当前切面类注册到spring容器中
@Component
// 标识当前类是一个切面类
@Aspect
public class LogAspect {

    /* 定义切入点表达式：匹配 任意返回值 com.moon.springsample.sevice.impl包下 任意类 任意方法 任意类型参数列表 */
    @Pointcut("execution(* com.moon.springsample.sevice.impl.*.*(..))")
    private void pt() {
    }

    /*
     * @Before注解用于配置当前方法是一个前置通知
     *  基于注解的AOP情况下：它是在切入点方法执行之前
     *  前置通知可以方法的参数，被对其进行预处理与增强，如果是多个参数，在args函数中定义即可
     *  argNames属性不写也同样效果
     *
     * 注：如果匹配了args方法参数，则会拦截有对应参数的方法，不再是类中任意方法，即此注解只拦截UserServiceImpl#saveUser(User user, String id)方法
     */
    @Before(value = "execution(* com.moon.springsample.sevice.impl.*.*(..)) && args(user, id)", argNames = "user,id")
    // @Before("execution(* com.moon.springsample.sevice.impl.*.*(..)) && args(user, id)")
    public void beforeLog(User user, String id) {
        id = UUID.randomUUID() + "|" + id;
        user.setId(id);
        System.out.println("前置通知(@Before)：获取id后修改为 = " + id);
        System.out.println("前置通知(@Before)：执行切入点方法前...记录日志");
    }

    /*
     * @AfterReturning注解用于配置当前方法是一个后置通知
     *  基于注解的AOP情况下：它是在切入点方法正常执行并返回之前执行（注意：此时已经执行完最终通知了）
     *  pointcut属性与value的作用完全一样
     *  returning属性可以获取方法的返回值
     */
    @AfterReturning(pointcut = "pt()", returning = "object")
    public void afterReturnLog(Object object) {
        if (object instanceof User) {
            User user = (User) object;
            System.out.println("后置通知(@AfterReturning)，获取到的返回值是" + user.toString());
        }
        System.out.println("后置通知(@AfterReturning)：执行切入点方法正常执行后并返回之前执行（执行完最终通知后）...记录日志");
    }

    /*
     * @AfterThrowing注解用于配置当前方法是一个异常通知
     *  基于注解的AOP情况下：它是在切入点方法执行产生异常之后执行（注意：此时已经执行完最终通知了）
     *  pointcut属性与value的作用完全一样
     *  throwing属性可以获取切入点方法出现的异常对象
     */
    @AfterThrowing(value = "pt()", throwing = "t")
    public void afterThrowingLog(Throwable t) {
        System.out.println("异常通知(@AfterThrowing)：执行切入点方法出现异常: " + t.getMessage());
    }

    /*
     * @After注解用于配置当前方法是一个最终通知
     *  基于注解的AOP情况下：它是在切入点方法执行之后执行
     *  与@Before注解一样，可以获取方法参数，如果指定了方法的参数列表，即只会拦截与其相应的方法
     *
     * 注：即此示例只拦截到UserServiceImpl#findById(String id)方法
     */
    @After(value = "execution(* com.moon.springsample.sevice.impl.*.*(..)) && args(id)", argNames = "id")
    public void afterLog(String id) {
        System.out.println("最终通知(@After)：获取到的方法入参id：" + id);
        System.out.println("最终通知(@After)：执行切入点方法完成后（不管有无异常都会执行）...记录日志");
    }

}
