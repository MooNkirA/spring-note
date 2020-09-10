package com.moon.springaop.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

/**
 * 注解 @EnableAspectJAutoProxy 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-7 23:24
 * @description
 */
public class SpringEnableAspecctJAutoProxyTest {

    @Test
    public void enableAspecctJAutoProxyasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.获取对象
        UserService userService = context.getBean("userService", UserService.class);
        // 3.执行方法
        User user = new User();
        user.setId("1");
        user.setUsername("石原里美");
        user.setNickname("十元");
        // 执行没有被切面切到的方法
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        userService.saveAllUser(users);
    }

}
