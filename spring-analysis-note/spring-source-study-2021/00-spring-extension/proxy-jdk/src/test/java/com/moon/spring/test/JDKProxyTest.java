package com.moon.spring.test;

import com.moon.spring.proxy.UserServiceAdvice;
import com.moon.spring.service.UserService;
import com.moon.spring.service.UserServiceImpl;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * JDK 动态代理测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-19 20:55
 * @description
 */
public class JDKProxyTest {

    @Test
    public void testJDKProxyBasic() {
        // 创建代理
        UserService userService = (UserService) Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(),
                new Class[]{UserService.class}, new UserServiceAdvice(new UserServiceImpl()));

        // 通过代理调用增加的方法
        String result = userService.addUserId("123");
        System.out.println("增强后方法返回的结果：" + result);
    }

}
