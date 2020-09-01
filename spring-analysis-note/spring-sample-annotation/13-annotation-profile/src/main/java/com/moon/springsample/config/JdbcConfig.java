package com.moon.springsample.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * 数据源对象配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 14:51
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
     * 开发环境的数据源
     *
     * @return
     */
    @Bean("dataSource")
    // @Profile注解是spring提供的一个用来标明当前运行环境的注解
    @Profile("dev")
    public DruidDataSource createDevDataSource() {
        // 1. 创建druid数据源
        DruidDataSource dataSource = new DruidDataSource();
        // 2. 设置数据源相关属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // 3. 设置开发环境的最大活跃连接数：5
        dataSource.setMaxActive(5);
        // 4. 返回对象
        return dataSource;
    }

    /**
     * 测试环境的数据源
     *
     * @return
     */
    @Bean("dataSource")
    @Profile("test")
    public DruidDataSource createTestDataSource() {
        // 1. 创建druid数据源
        DruidDataSource dataSource = new DruidDataSource();
        // 2. 设置数据源相关属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // 3. 设置测试环境的最大活跃连接数：50
        dataSource.setMaxActive(50);
        // 4. 返回对象
        return dataSource;
    }

    /**
     * 生产环境的数据源
     *
     * @return
     */
    @Bean("dataSource")
    @Profile("pro")
    public DruidDataSource createProDataSource() {
        // 1. 创建druid数据源
        DruidDataSource dataSource = new DruidDataSource();
        // 2. 设置数据源相关属性
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // 3. 设置生产环境的最大活跃连接数：150
        dataSource.setMaxActive(150);
        // 4. 返回对象
        return dataSource;
    }
}
