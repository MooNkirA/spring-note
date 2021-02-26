package com.moon.spring.test;

import com.moon.spring.config.SpringConfig;
import com.moon.spring.service.LogService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Aop 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-25 23:00
 * @description
 */
public class AopTest {

    private final ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    /**
     * 基于注解方式的aop测试 - @Around环绕增强
     */
    @Test
    public void testAspectOnAnnotationAround() {
        LogService logService = context.getBean(LogService.class);
        logService.logErrorMessage("You have an error!");
    }

}
