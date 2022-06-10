package com.moon.springmvc.test;

import org.junit.Test;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * HandlerMapping 示例测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 8:50
 * @description
 */
public class HandlerMappingTest {

    @Test
    public void testBasic() throws Exception {
        // 创建 Spring boot 中 servlet web 环境容器，在配置类中手动创建 tomcat 实例
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext("com.moon.springmvc");
        // 从容器中获取 RequestMappingHandlerMapping
        // 该对象用于解析 @RequestMapping 以及派生注解，生成路径与控制器方法的映射关系, 在 web 容器初始化时就生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
        /*
         * 获取处理器映射器中所有映射
         *  RequestMappingInfo：请求的映射信息
         *  HandlerMethod：处理方法的信息
         */
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
            System.out.println(requestMappingInfo + " = " + handlerMethod);
        });

        // 根据请求。从映射处理器对象中，根据请求获取处理链（因为一个请求可能会包含若干个过滤器）
        HandlerExecutionChain chain = handlerMapping.getHandler(new MockHttpServletRequest("GET", "/demo/hello"));
        System.out.println("处理器执行链对象: " + chain);
    }

}
