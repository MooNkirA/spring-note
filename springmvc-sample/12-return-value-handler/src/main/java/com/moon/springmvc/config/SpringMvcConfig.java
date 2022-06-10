package com.moon.springmvc.config;

import com.moon.springmvc.handler.CustomReturnValueHandler;
import com.moon.springmvc.handler.MyHandlerAdapter;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Arrays;

/**
 * Spring MVC 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 10:17
 * @description
 */
@Configuration
@ComponentScan("com.moon.springmvc")
public class SpringMvcConfig {

    /*
     * DispatcherServlet 初始化时默认添加 RequestMappingHandlerMapping 组件，但只保存在 DispatcherServlet 类的属性中
     * 为了方便测试，因此不使用默认创建，手动创建并加入到 Spring 容器
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    /*
     * DispatcherServlet 初始化时默认添加 RequestMappingHandlerAdapter 组件，但只保存在 DispatcherServlet 类的属性中
     * 为了方便测试，因此不使用默认创建，手动创建并加入到 Spring 容器
     */
    @Bean
    public MyHandlerAdapter requestMappingHandlerAdapter() {
        MyHandlerAdapter handlerAdapter = new MyHandlerAdapter();
        // 加入自定义返回值处理器
        handlerAdapter.setCustomReturnValueHandlers(Arrays.asList(new CustomReturnValueHandler()));
        return handlerAdapter;
    }

    // 创建内嵌 web 容器工厂
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        return new TomcatServletWebServerFactory(8080);
    }

    // 创建 DispatcherServlet
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    // 注册 DispatcherServlet, Spring MVC 的入口
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    /*
     * ************************************************
     *   配置 FreeMarker
     * ************************************************
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setDefaultEncoding("utf-8");
        configurer.setTemplateLoaderPath("classpath:templates");
        return configurer;
    }

    @Bean // FreeMarkerView 在借助 Spring 初始化时，会要求 web 环境才会走 setConfiguration, 这里想办法去掉了 web 环境的约束
    public FreeMarkerViewResolver viewResolver(FreeMarkerConfigurer configurer) {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver() {
            @Override
            protected AbstractUrlBasedView instantiateView() {
                FreeMarkerView view = new FreeMarkerView() {
                    @Override
                    protected boolean isContextRequired() {
                        return false;
                    }
                };
                view.setConfiguration(configurer.getConfiguration());
                return view;
            }
        };
        resolver.setContentType("text/html;charset=utf-8");
        resolver.setPrefix("/");
        resolver.setSuffix(".ftl");
        resolver.setExposeSpringMacroHelpers(false);
        return resolver;
    }
}
