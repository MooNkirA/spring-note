package com.moon.springmvc.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * web项目配置类，用于初始化 Spring 和SpringMVC ioc容器的配置
 * 注：以下配置类不需要加任何注解，因为它是基于 Servlet3.0 规范，在web项目启动处理完此类的逻辑再到spring与springmvc的容器创建
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-27 9:43
 * @description
 */
// 配置方式1：实现 WebApplicationInitializer 接口
/*public class WebConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 创建基于注解的上下文容器
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfiguration.class);

        // 注册并初始化 DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }

}*/

// 配置方式2：继承 WebApplicationInitializer 接口的抽象实现 AbstractDispatcherServletInitializer
public class WebConfig extends AbstractDispatcherServletInitializer {

    /**
     * 重写web项目启动方法，可自定义的初始化处理
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 触发父类的onStartup方法
        super.onStartup(servletContext);
        // 下面可以做其他初始化相关的工作
    }

    /* 用于创建 SpringMVC 的 ioc 容器 WebApplicationContext */
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        // 创建基于注解的web应用上下文容器
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 将SpringMVC配置类注册到web容器中
        context.register(SpringConfiguration.class);
        // 返回容器对象，完成创建
        return context;
    }

    /*
     * 用于指定DispatcherServlet的请求映射
     *  相当于web.xml中配置的
     *  <servlet-mapping>
     *      <servlet-name>dispatcherServlet</servlet-name>
     *      <url-pattern>/</url-pattern>
     *  </servlet-mapping>
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /* 用于创建Spring的ioc容器（即根容器，非web层的对象容器） */
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 如果不需要，返回 null 即可
        return null;
    }

    /* 添加自定义过滤器 */
    @Override
    protected Filter[] getServletFilters() {
        return super.getServletFilters();
    }
}