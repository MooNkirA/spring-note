package com.moon.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * SpringMVC 的配置类，用于替代springmvc.xml配置文件
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-17 14:44
 * @description
 */
@Configuration // 标识为配置类
// 配置包扫描，专注于扫描springmvc相关的包
@ComponentScan("com.moon.springmvc.web")
// 开启对SpringMVC的注解支持，该注解会引入DelegatingWebMvcConfiguration配置类，该类会创建很功能增强的对象
@EnableWebMvc
public class SpringMvcConfiguration implements WebMvcConfigurer {

    /**
     * 配置添加资源处理规则
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**")   // 配置静态资源的映射
                .addResourceLocations("/js/")   // 静态资源所在项目的路径
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    /**
     * 创建视图解析器(InternalResourceViewResolver)并存入ioc容器
     *
     * @return ViewResolver
     */
    @Bean
    public ViewResolver createViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 配置视图的公共目录路径(前缀)
        viewResolver.setPrefix("/WEB-INF/pages/");
        // 配置视图的扩展名称(后缀)
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
