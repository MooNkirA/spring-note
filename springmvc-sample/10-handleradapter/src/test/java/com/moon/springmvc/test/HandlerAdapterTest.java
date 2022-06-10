package com.moon.springmvc.test;

import com.moon.springmvc.common.Constants;
import com.moon.springmvc.handler.MyHandlerAdapter;
import org.junit.Test;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.StandardCharsets;

/**
 * HandlerAdapter 示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 8:53
 * @description
 */
public class HandlerAdapterTest {

    // 模拟基础处理器适配器调用流程
    @Test
    public void testBasic() throws Exception {
        // 创建 Spring boot 中 servlet web 环境容器，在配置类中手动创建 tomcat 实例
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(Constants.BASE_PACKAGES);
        // 从容器中获取 RequestMappingHandlerMapping
        // 该对象用于解析 @RequestMapping 以及派生注解，生成路径与控制器方法的映射关系, 在 web 容器初始化时就生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);

        // 模拟的请求
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("GET", "/demo/hello");
        // 模拟的响应
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        // 从映射处理器中，根据请求获取处理链（因为一个请求可能会包含若干个过滤器）
        HandlerExecutionChain chain = handlerMapping.getHandler(mockRequest);
        System.out.println("处理器执行链对象: " + chain);

        // 获取 RequestMappingHandlerAdapter
        MyHandlerAdapter handlerAdapter = context.getBean(MyHandlerAdapter.class);
        // 通过处理器适配器调用相应的控制器方法
        handlerAdapter.invokeHandlerMethod(mockRequest, mockResponse, (HandlerMethod) chain.getHandler());

        // 检查响应
        byte[] content = mockResponse.getContentAsByteArray();
        System.out.println(new String(content, StandardCharsets.UTF_8));

        // 查看所有内置的参数解析器
        System.out.println("*********** 所有参数解析器 ***********");
        for (HandlerMethodArgumentResolver resolver : handlerAdapter.getArgumentResolvers()) {
            System.out.println(resolver);
        }

        // 查看所有内置的返回值解析器
        System.out.println("*********** 所有返回值解析器 ***********");
        for (HandlerMethodReturnValueHandler handler : handlerAdapter.getReturnValueHandlers()) {
            System.out.println(handler);
        }
    }

}
