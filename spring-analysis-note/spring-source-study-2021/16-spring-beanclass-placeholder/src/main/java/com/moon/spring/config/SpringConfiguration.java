package com.moon.spring.config;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 19:55
 * @description
 */
@Configuration
public class SpringConfiguration implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /* 通过@Bean注解，创建占位符解析器 */
    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        // 通过ResourceLoader对象读取配置文件，并设置到PropertySourcesPlaceholderConfigurer占位符解析器的location属性
        propertySourcesPlaceholderConfigurer.setLocation(resourceLoader.getResource("application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

}
