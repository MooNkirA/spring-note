package com.moon.springmvc.config;

import com.moon.springmvc.web.interceptors.MyInterceptor1;
import com.moon.springmvc.web.interceptors.MyInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
        registry.addResourceHandler("/js/**", "/images/**", "/css/**")   // 配置静态资源的映射
                .addResourceLocations("/js/", "/images/", "/css/")   // 静态资源所在项目的路径
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

    // 注入自定义拦截器
    @Autowired
    private MyInterceptor1 myInterceptor1;
    @Autowired
    private MyInterceptor2 myInterceptor2;

    /**
     * 注册拦截器方法
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // InterceptorRegistry中注册拦截器（注：此处的注册顺序决定了多个拦截器的执行顺序）
        // registry.addInterceptor(myInterceptor2);
        // registry.addInterceptor(myInterceptor1);
    }
}
