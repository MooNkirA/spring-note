package com.moon.springannotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * 注解 @Conditional 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-31 23:23
 * @description
 */
public class SpringConditionalTest {

    @Test
    public void conditionalBasicTest() {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample.config");
        // 2. 获取容器中的bean对象
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        // 3. 输出对象
        System.out.println(dataSource);
    }

}
