package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.sevice.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @Primary 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 15:40
 * @description
 */
public class SpringPrimaryTest {

    @Test
    public void primaryBasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 获取容器中的bean对象
        AccountService accountService = context.getBean("accountService", AccountService.class);
        // 3. 调用方法
        accountService.save();
    }
}
