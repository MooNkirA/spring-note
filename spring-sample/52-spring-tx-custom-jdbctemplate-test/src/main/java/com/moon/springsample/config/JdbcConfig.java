package com.moon.springsample.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.moon.springsample.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * JDBC 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 15:04
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
     * 创建 DataSource 数据源对象，并注册到ioc容器中
     *
     * @return
     */
    @Bean("dataSource")
    public DataSource createDataSource() {
        // 1. 创建druid数据源
        DruidDataSource dataSource = new DruidDataSource();
        // 2. 配置数据源相关参数
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 创建自定义的 JDBCTemplate 持久层操作对象，并注册到ioc容器中
     *
     * @param dataSource
     * @return
     */
    @Bean("jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
