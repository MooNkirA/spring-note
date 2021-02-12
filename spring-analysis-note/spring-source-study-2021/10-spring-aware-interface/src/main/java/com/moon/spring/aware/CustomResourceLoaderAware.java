package com.moon.spring.aware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * ResourceLoaderAware 接口运用示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 18:18
 * @description
 */
@Component
public class CustomResourceLoaderAware implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("CustomResourceLoaderAware.setResourceLoader()方法执行了....");
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
