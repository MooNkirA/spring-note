package com.moon.springsample.aspect;

import com.moon.springsample.domain.User;
import com.moon.springsample.extension.ValidateExtensionService;
import com.moon.springsample.extension.impl.ValidateExtensionServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
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

    /*
     * @DeclareParents注解让目标类具备当前声明接口中的方法，相当于多实现了一个接口，实现的逻辑就在defaultImpl属性指定，
     * 底层也是使用动态代理实现
     *      value属性：用于指定目标类型的表达式。当在全限定类名后面跟上”+“时，表示当前类及其子类
     *      defaultImpl属性：指定提供方法或者字段的默认实现类，此示例为校验扩展实现类
     */
    @DeclareParents(value = "com.moon.springsample.service.UserService+", defaultImpl = ValidateExtensionServiceImpl.class)
    private ValidateExtensionService validateExtensionService;

    /*
     * 第二种触发@DeclareParents注解的方式
     *   通过this关键字，将扩展接口的对象引用做为通知方法形参
     *   而args函数是切入点方法的形参，两个不能搞混
     */
    @Before("execution(* com.moon.springsample.service.impl.*.*(..))&&this(validateExtensionService)&&args(user)")
    public void beforeLog(ValidateExtensionService validateExtensionService, User user) {
        // 调用扩展接口的方法
        if (validateExtensionService.checkUser(user)) {
            // 校验通过
            System.out.println("beforeLog()前置通知方法执行了...");
        } else {
            // 校验不通过，抛出异常
            throw new IllegalArgumentException("用户昵称包含非法字符！");
        }
    }

}
