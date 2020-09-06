package com.moon.springsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;

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

    /**
     * 获取数据库连接对象
     * 注意：如果使用@Bean注解，直接通过dataSource.getConnection()获取连接对象注册到spring容器中，
     * 因为此时连接对象是单例的，实现项目中是多线程的，这样会导致每个线程拿到的连接都是同一个，就无法实现事务的隔离性，所以不能这么操作
     *
     * @param dataSource 数据源
     * @return 数据连接对象
     */
    @Bean("connection")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Connection getConnection(@Autowired DataSource dataSource) {
        // 1. 初始化事务同步管理器
        TransactionSynchronizationManager.initSynchronization();
        // 2. 使用spring的数据源工具类获取当前线程的连接，返回连接对象并注册到容器中
        return DataSourceUtils.getConnection(dataSource);
    }

}
