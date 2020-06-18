package com.moon.spring.test;

import com.moon.spring.config.ComponentScanConfig;
import com.moon.spring.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring AOP 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-17 23:18
 * @description
 */
public class AopTest {

    private ApplicationContext context;

    @Before
    public void before() {
        // 使用注解扫描方式启动spring容器，ComponentScanConfig配置类有@ComponentScan注解
        context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
    }

    /**
     * 基于注解方式的aop测试 - @Around环绕增强
     */
    @Test
    public void aspectOnAnnotationAroundTest() {
        UserService userService = context.getBean(UserService.class);
        userService.queryUser("MooNkirA");
    }

}
