package com.moon.springmvc.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * web项目配置类，用于初始化Spring和SpringMVC ioc容器的配置
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-21 08:44
 * @description
 */
public class WebConfig extends AbstractDispatcherServletInitializer {

    /**
     * 重写web项目启动方法，增加注册字符集过滤器
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 触发父类的onStartup方法
        super.onStartup(servletContext);
        // 1. 创建字符集过滤器对象
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        // 2. 设置使用的字符集
        characterEncodingFilter.setEncoding("UTF-8");
        // 3. 添加到容器（注：此容器不是ioc容器，而是ServletContainer）
        FilterRegistration.Dynamic registration = servletContext.addFilter("characterEncodingFilter", characterEncodingFilter);
        // 4. 给过滤器添加映射规则
        registration.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE),
                false, "/*");
    }

    /*
     * 用于创建SpringMVC的ioc容器
     */
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        // 创建基于注解的web应用上下文容器
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 将SpringMVC配置类注册到web容器中
        context.register(SpringMvcConfiguration.class);
        // 返回容器对象，完成创建
        return context;
    }

    // 用于指定DispatcherServlet的请求映射，相当于web.xml中配置的
    @Override
    protected String[] getServletMappings() {
        // 配置拦截的url
        return new String[]{"/"};
    }

    /*
     * 用于创建Spring的ioc容器（即根容器，非web层的对象容器）
     */
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 创建基于注解的web应用上下文容器
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 将Spring配置类注册到web容器中
        context.register(SpringMvcConfiguration.class);
        // 返回容器对象，完成创建
        return context;
    }
}
