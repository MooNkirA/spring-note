package com.moon.springsample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 连接数据库的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-29 21:48
 * @description
 */
public class JdbcConfig {

    // @Value注解用于注入基本类型和String类型的数据。它支持spring的EL表达式，可以通过${}的方式获取配置文件中的数据
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /* 创建JdbcTemplate对象 */
    @Bean("jdbcTemplate") // @Bean注解用于方法上，表示把当前方法的返回值存入spring的ioc容器
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /* 创建DataSource对象 */
    @Bean("dataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
