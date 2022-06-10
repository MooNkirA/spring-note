package com.moon.springsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 将连接数据库的相关配置独立到一个配置类中
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 10:42
 * @description
 */
/*
 * Spring框架会先扫描到标识有@Configuration,@Component等注解的类，加载到ioc容器后，该类中的@Bean注解才会再被扫描及实例化对象加载到容器中
 *  但抽取模块的配置独立到一个配置类中，就是为了达到分类管理目的，并且只想将关注点在配置内容上，
 *  所以一般不建议每个独立模块的配置类上加@Configuration,@Component等注解，并且也不想让项目扫描配置类时手动指定每个模块。故最好使用@Import注解导入到主配置类中
 */
// @Configuration
public class JdbcConfig {

    /* 创建DataSource对象 */
    @Bean("dataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 配置数据源相关参数 （注：此处为了暂时不涉及@PropertySource注解，所以直接将相关的配置值硬编码！）
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tempdb?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

}
