package com.moon.springsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Jdbc 配置类，包含如下配置：
 * <p>JDBCTemplate 的创建与配置
 * <p>dataSource 的创建与配置
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 21:39
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

    /**
     * 创建 JDBCTemplate 持久层操作对象，并注册到ioc容器中
     *
     * @param dataSource
     * @return
     */
    @Bean("jdbcTemplate")
    // 方法的入参无论是否加上@Autowired 注解，都会自动注入数据源对象
    public JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 创建 DataSource 数据源对象，并注册到ioc容器中
     *
     * @return
     */
    @Bean("dataSource")
    public DataSource createDataSource() {
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
