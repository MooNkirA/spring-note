package com.moon.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Spring 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 19:55
 * @description
 */
@Configuration
@ComponentScan("com.moon.spring")
public class SpringConfiguration {

    /* 通过@Bean注解，创建占位符解析器 */
    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        // 读取本地配置文件，设置占位符解析器location属性
        ClassPathResource resource = new ClassPathResource("application.properties");
        propertySourcesPlaceholderConfigurer.setLocation(resource);
        return propertySourcesPlaceholderConfigurer;
    }

}
