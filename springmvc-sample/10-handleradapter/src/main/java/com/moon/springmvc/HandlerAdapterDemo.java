package com.moon.springmvc;

import com.moon.springmvc.config.SpringConfiguration;
import com.moon.springmvc.handler.MyHandlerAdapter;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * HandlerMapping 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-27 20:03
 * @description
 */
public class HandlerAdapterDemo {

    public static void main(String[] args) {
        // 创建 Spring boot 中 servlet web 环境容器，在配置类中手动创建 tomcat 实例
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(SpringConfiguration.class);
        // 创建 RequestMappingHandlerMapping
        MyHandlerAdapter handlerAdapter = context.getBean(MyHandlerAdapter.class);
        handlerAdapter.invokeHandlerMethod()
    }

}
