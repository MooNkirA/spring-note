package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 22:26
 * @description
 */
@Configuration // 标识配置类
@ComponentScan("com.moon.springsample") // 开启包扫描
@Import({JdbcConfig.class, TransactionManagerConfig.class}) // 导入jdbc配置类与事务管理器的配置类
@PropertySource("classpath:jdbc.properties") // 引入数据库连接配置文件
/* 开启spring注解事务的支持 */
@EnableTransactionManagement
public class SpringConfiguration {
}
