package com.moon.springsample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Spring 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-18 9:52
 * @description
 */
@Configuration
@ComponentScan("com.moon.springsample")
public class SpringConfiguration {

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("SpringConfiguration 配置类注入 ApplicationContext");
    }

    @PostConstruct
    public void init() {
        System.out.println("SpringConfiguration 使用 @PostConstruct 注解初始化");
    }

    // 注释或添加 beanFactory 后处理器观察 @Autowired 与 @PostConstruct 注解是否执行
    // 可以将方法修改为 static 修饰以避免这些容器生命周期问题
    @Bean
    public BeanFactoryPostProcessor postProcessor() {
        return beanFactory -> {
            System.out.println("SpringConfiguration 配置类注入 BeanFactoryPostProcessor");
        };
    }
}
