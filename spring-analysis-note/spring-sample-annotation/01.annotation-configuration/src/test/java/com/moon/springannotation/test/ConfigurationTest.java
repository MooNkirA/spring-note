package com.moon.springannotation.test;

import com.moon.springsample.config.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * `@Configuration` 注解使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-31 23:40
 * @description
 */
public class ConfigurationTest {

    /* Configuratio注解使用测试 */
    public static void main(String[] args) {
        // 方式一：1. 获取基于注解的spring容器，使用基础包basePackages的构造函数创建容器，此时SpringConfiguration类上必须加上@Configuration注解
        // ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample");

        // 方式二：1. 获取基于注解的spring容器，使用传入字节码的构造函数创建容器，此时SpringConfiguration类上可以不加@Configuration注解
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        // 2. 根据id或者类型去获取对应的bean实例
        SpringConfiguration springConfiguration = (SpringConfiguration) context.getBean("springConfiguration");

        System.out.println(springConfiguration);
    }

}
