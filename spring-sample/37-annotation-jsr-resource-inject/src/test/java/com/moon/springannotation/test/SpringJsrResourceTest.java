package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import com.moon.springsample.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * JSR 注解 @Resource 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 14:14
 * @description
 */
public class SpringJsrResourceTest {

    @Test
    public void resourceBasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 获取容器中的bean对象
        AccountService accountService = context.getBean("accountService", AccountService.class);
        // 3. 调用方法
        accountService.save();
    }

}
