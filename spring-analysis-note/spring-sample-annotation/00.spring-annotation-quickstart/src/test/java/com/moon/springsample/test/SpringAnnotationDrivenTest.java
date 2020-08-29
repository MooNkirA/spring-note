package com.moon.springsample.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * spring注解驱动开发快速入门示例测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-29 22:14
 * @description
 */
public class SpringAnnotationDrivenTest {

    /* 测试spring全注解开发示例 */
    public static void main(String[] args) {
        // 1. 获取基于注解的spring容器
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample");
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        // 3. 进行数据库操作
        jdbcTemplate.update("insert into account(name,money) values (?,?)","Moon", 1888);
    }

}
