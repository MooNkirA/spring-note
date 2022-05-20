package com.moon.springaop.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring Aspect 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 22:38
 * @description
 */
public class SpringAOPTest {

    /* 常规 Spring 实现 AOP 测试 */
    @Test
    public void testSpringBasicAop() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2.获取对象
        UserService userService = context.getBean("userService", UserService.class);
        // 3.执行方法
        User user = new User();
        user.setId("1");
        user.setUsername("石原里美");
        user.setNickname("十元");
        userService.saveUser(user);
    }

}
