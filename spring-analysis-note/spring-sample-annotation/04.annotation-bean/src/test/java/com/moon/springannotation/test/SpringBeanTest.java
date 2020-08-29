package com.moon.springannotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *  注解 @Bean 基础使用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 09:25
 * @description
 */
public class SpringBeanTest {

    /* @Bean注解基础使用测试 */
    @Test
    public void beanAnnotaionBasicTest() {
        // 1. 获取基于注解的spring容器，使用基础包basePackages的构造函数创建容器
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample");
        // 2. 获取@Bean注解存入的对象实例
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        // 输出结果
        System.out.println(dataSource);
    }

    /* @Bean注解的autowireCandidate属性使用测试 */
    @Test
    public void autowireCandidateTest() {
        // 1. 获取基于注解的spring容器，使用基础包basePackages的构造函数创建容器
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample");
        // 2. 获取@Bean注解存入的对象实例
        JdbcTemplate jdbcTemplate = context.getBean("createJdbcTemplate", JdbcTemplate.class);
        /*
         * 输出结果
         *   如果返回DataSource对象的方法的@Bean注解设置属性autowireCandidate = false，
         *   则通过@Autowired注解无法自动注入DataSource对象，程序运行会报ioc容器找不到DataSource对象 "No qualifying bean of type 'javax.sql.DataSource' available"
         *
         *   但可以使用@Resource注解注入到成员变量的方式来获取DataSource对象，autowireCandidate属性不会影响@Resource注解
         */
        System.out.println(jdbcTemplate);
    }

}
