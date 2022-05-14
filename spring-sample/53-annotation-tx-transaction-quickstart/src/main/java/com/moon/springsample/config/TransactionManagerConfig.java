package com.moon.springsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * 事务管理器的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 23:18
 * @description
 */
public class TransactionManagerConfig {

    // 创建PlatformTransactionManager事务管理器，注册到ioc容器中
    @Bean
    public PlatformTransactionManager createPlatformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // 创建TransactionTemplate事务模板对象，注册到ioc容器中
    @Bean
    public TransactionTemplate createTransactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

}
