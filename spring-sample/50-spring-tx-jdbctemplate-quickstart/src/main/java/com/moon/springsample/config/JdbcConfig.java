package com.moon.springsample.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;

/**
 * JDBC 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-13 11:04
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
        dataSource.setDriverClassName(this.driver);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

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
     * 创建 LobHandler 对象，并注册到ioc容器中
     *
     * @return
     */
    @Bean("lobHandler")
    public LobHandler createLobHandler() {
        // 直接创建spring框架的提供的LobHandler实现类
        return new DefaultLobHandler();
    }

    /**
     * 创建 NamedParameterJdbcTemplate 对象，并注册到ioc容器中
     *
     * @param jdbcTemplate
     * @return
     */
    @Bean("namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
        // NamedParameterJdbcTemplate其实就是对JdbcTemplate进行再次封装
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

}
