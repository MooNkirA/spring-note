package com.moon.springsample.config;

import com.moon.springsample.component.Component3;
import com.moon.springsample.component.Component4;
import com.moon.springsample.component.Component5;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

/**
 * 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 15:32
 * @description
 */
@Configuration
@ComponentScan("com.moon.springsample.component") // 配置包扫描
public class SpringConfiguration {

    @Bean
    public Component3 component3() {
        return new Component3();
    }

    @Bean
    public Component4 component4(Component5 component5) {
        return new Component4(component5);
    }

    @Bean(initMethod = "init")
    public Component5 component5() {
        return new Component5();
    }

    // 测试实现 mybatis 接口扫描生成代理实例专用
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/tempdb");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

}
