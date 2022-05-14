package com.moon.springsample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * JDBC 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 08:03
 * @description
 */
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /* 创建DataSource对象 */
    @Bean("dataSource")
    public DataSource createDataSource() {
        System.out.println("createDataSource()方法获取到的驱动driver是" + driver);
        // 1. 创建Spring的内置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 2. 配置数据源相关参数
        dataSource.setDriverClassName(this.driver);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

}
