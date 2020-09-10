package com.moon.springsample.aspect;

import com.moon.springsample.domain.SystemLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
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

    /* 定义切入点表达式 */
    @Pointcut("execution(* com.moon.springsample.service.impl.*.*(..))")
    private void pt() {
    }

    /*
     * @Around注解用于配置当前方法是一个环绕通知
     *  注意：如果将通知的方法返回设置为void，而拦截增强的方法是有返回的情况下，经过通知增加后，原来的方法是获取不到返回值的
     *      所以一般都是定义返回Object类型即可
     */
    /*@Around("pt()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) {
        // 定义返回值
        Object retValue = null;

        try {
            // 前置通知
            System.out.println("环绕通知(@Around)：执行切入点方法之前...记录日志");

            // 获取切入点方法执行所需的参数
            Object[] args = joinPoint.getArgs();
            // 执行切入点的方法（就算不传入切入点方法的的参数，也是可以正常执行的）
            retValue = joinPoint.proceed(args);

            // 后置通知
            System.out.println("环绕通知(@Around)：执行切入点方法之后...记录日志");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            // 异常通知
            System.out.println("环绕通知(@Around)：执行切入点方法产生异常后记录日志");
        } finally {
            // 最终通知
            System.out.println("环绕通知(@Around)：无论切入点方法执行是否有异常都记录日志");
        }
        return retValue;
    }*/

    /**
     * 注解 @Around 使用案例
     *
     * @param joinPoint
     * @return
     */
    @Around("pt()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) {
        // 定义返回值
        Object retValue = null;
        try {
            // 创建系统日志对象
            SystemLog systemLog = new SystemLog();
            // 设置主键
            String id = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            systemLog.setId(id);
            // 设置来访者ip（java工程，没有请求信息，实际web项目，可以通过Request对象获取）
            systemLog.setRemoteIP("127.0.0.1");
            // 设置执行时间
            systemLog.setTime(new Date());

            /* 设置当前执行的方法名称、方法描述信息 */
            // 1. 使用ProceedingJoinPoint接口中的获取签名方法
            Signature signature = joinPoint.getSignature();
            // 2. 判断当前签名是否方法签名类型
            if (signature instanceof MethodSignature) {
                // 3. 把签名转成方法签名
                MethodSignature methodSignature = (MethodSignature) signature;
                // 4. 获取当前执行的方法对象
                Method method = methodSignature.getMethod();
                // 5. 获取方法名称
                String methodName = method.getName();
                // 6. 设置系统日志对象的方法名称属性
                systemLog.setMethod(methodName);

                // 7. 判断当前方法上是否有@Description注解
                if (method.isAnnotationPresent(Description.class)) {
                    // 8. 获取得到当前方法上的@Description注解
                    Description description = method.getAnnotation(Description.class);
                    // 9. 获取注解的value属性
                    String value = description.value();
                    // 10. 设置系统日志对象的方法说明属性赋值
                    systemLog.setAction(value);
                }
            }

            // 切入点方法执行前记录日志（实际项目是保存到日志，此处只进行模拟）
            System.out.println("环绕通知(@Around)执行了记录日志：" + systemLog.toString());

            // 获取切入点方法执行所需的参数
            Object[] args = joinPoint.getArgs();
            // 执行切入点的方法（就算不传入切入点方法的的参数，也是可以正常执行的）
            retValue = joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            // 异常处理
            System.out.println("环绕通知(@Around)：增强方法异常处理");
        }
        // 返回
        return retValue;
    }

}
