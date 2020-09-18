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
 * Servlet3.0规范提供的标准接口ServletContainerInitializer，作用是在启动容器是做一些初始化操作，
 * 所以创建`WebConfig`类可以实现ServletContainerInitializer接口，
 * 或者继承SpringMVC提供的抽象实现AbstractDispatcherServletInitializer类，
 * 重写里面的onStartUp()方法，调用执行父类的onStartUp()方法的同时，执行自己项目需要的一些初始化操作
 * <p>
 * 用于初始化Spring和SpringMVC ioc容器的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-17 15:36
 * @description
 */
/* 注：此类不需要加任何注解，因为它是基于Servlet3.0规范，在web项目启动处理完此类的逻辑再到spring与springmvc的容器创建 */
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
        /*
         * 4. 给过滤器添加映射规则
         *      第1个参数是拦截的类型
         *      第2个参数是此过滤器的优先级设置，
         *          true：就是此过滤器在web.xml配置的过滤器之后加载
         *          false：就是此过滤器在web.xml配置的过滤器之前加载
         *      第3个参数是拦截的url，"/*"代表拦截所有
         */
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
        /*
         * 配置拦截的url，说明：
         *  1.*.do，表示以.do结尾的请求，进入前端控制器
         *  2./，表示所有请求都进入前端控制器
         */
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
