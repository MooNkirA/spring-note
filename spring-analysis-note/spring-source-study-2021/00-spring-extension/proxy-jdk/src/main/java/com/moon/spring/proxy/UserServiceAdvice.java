package com.moon.spring.proxy;

import com.moon.spring.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理类，代理 UserServiceImpl 类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 20:50
 * @description
 */
public class UserServiceAdvice implements InvocationHandler {

    private UserService userService;

    public UserServiceAdvice(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 前置增强
        before(args);
        // 被代理方法
        Object value = method.invoke(userService, args);
        // 后置增强
        return after(value);
    }

    private void before(Object[] args) {
        System.out.println("===========前置增强==========");
        if (args == null || args[0] == null || "".equals(((String) args[0]).trim())) {
            throw new RuntimeException("");
        }
    }

    private Object after(Object id) {
        System.out.println("===========后置增强=============");
        if (id instanceof String) {
            return "用户的ID：" + (String) id;
        }
        return id;
    }


}
