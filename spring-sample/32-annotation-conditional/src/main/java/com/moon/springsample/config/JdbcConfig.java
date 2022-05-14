package com.moon.springsample.config;

import com.moon.springsample.condition.LinuxCondition;
import com.moon.springsample.condition.WindowCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 数据库连接对象
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-31 23:16
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

    /* 创建window系统环境下的DataSource对象 */
    @Bean("dataSource")
    // 指定创建的bean对象注册到容器的条件，注解的参数是一个或者多个Condition接口的实现类，实现类中需要编写具体代码实现是否注册到ioc容器的条件。
    @Conditional(WindowCondition.class)
    public DataSource createWindowsDataSource() {
        // 1. 创建Spring的内置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 2. 配置数据源相关参数
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // 输出当前系统环境的url
        System.out.println("createWindowsDataSource()方法执行，Window URL is: " + url);
        return dataSource;
    }

    /* 创建linux系统环境下的DataSource对象 */
    @Bean("dataSource")
    @Conditional(LinuxCondition.class)
    public DataSource createLinuxDataSource(@Value("${linux.driver}") String linuxDriver,
                                            @Value("${linux.url}") String linuxUrl,
                                            @Value("${linux.username}") String linuxUsername,
                                            @Value("${linux.password}") String linuxPassword) {
        // 1. 创建Spring的内置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 2. 配置数据源相关参数
        dataSource.setDriverClassName(linuxDriver);
        dataSource.setUrl(linuxUrl);
        dataSource.setUsername(linuxUsername);
        dataSource.setPassword(linuxPassword);
        // 输出当前系统环境的url
        System.out.println("createLinuxDataSource()方法执行，Linux URL is: " + linuxUrl);
        return dataSource;
    }
}
