package com.moon.springannotation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 注解 @PropertySource 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 08:10
 * @description
 */
public class SpringPropertySourceTest {

    @Test
    public void propertySourceBasicTest() throws Exception {
        // 1. 创建注解扫描的容器
        ApplicationContext context = new AnnotationConfigApplicationContext("com.moon.springsample");
        // 2. 获取数据源对象
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        // 3. 操作数据源获取连接
        Connection connection = dataSource.getConnection();
        connection.close();
    }

}
