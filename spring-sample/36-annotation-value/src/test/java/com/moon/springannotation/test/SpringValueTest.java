package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @Value 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 11:07
 * @description
 */
public class SpringValueTest {

    @Test
    public void valueBasicTest(){
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 2. 获取配置类对象对象
        SpringConfiguration configuration = context.getBean("springConfiguration", SpringConfiguration.class);
        // 3. 输出使用@Value注入的相关属性值
        System.out.println(configuration.getColor());
        System.out.println(configuration.getName());
        System.out.println(configuration.getNumber());
    }

}
