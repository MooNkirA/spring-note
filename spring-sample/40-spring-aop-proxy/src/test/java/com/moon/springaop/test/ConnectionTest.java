package com.moon.springaop.test;

import com.moon.springsample.config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

/**
 * 数据库连接对象与线程绑定测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-5 16:07
 * @description
 */
public class ConnectionTest {

    @Test
    public void threadConnectionTest() {
        // 1. 创建容器
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                // 2. 从容器中获取连接对象
                Connection connection = context.getBean("connection", Connection.class);
                // 3. 输出对象（观察每个线程获取的连接对象的引用地址是否不一样）
                System.out.println(connection);
            }).start();
        }
        /*// 2. 从容器中获取连接对象
        Connection connection = context.getBean("connection", Connection.class);
        // 3. 输出对象（使用debug多线程模式运行，观察每个线程获取的连接对象的引用地址是否不一样）
        System.out.println(connection);*/
    }

}
