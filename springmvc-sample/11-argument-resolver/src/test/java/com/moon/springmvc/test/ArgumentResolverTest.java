package com.moon.springmvc.test;

import com.moon.springmvc.config.SpringMvcConfig;
import com.moon.springmvc.handler.MyHandlerAdapter;
import org.junit.Test;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.StandardCharsets;

/**
 * 参数解析器 示例测试
 * <p>
 * Spring MVC 内置的参数解析器
 * <p>
 * org.springframework.web.method.annotation.RequestParamMethodArgumentResolver
 * org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.PathVariableMapMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.MatrixVariableMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.MatrixVariableMapMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.RequestPartMethodArgumentResolver
 * org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver
 * org.springframework.web.method.annotation.RequestHeaderMapMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.ServletCookieValueMethodArgumentResolver
 * org.springframework.web.method.annotation.ExpressionValueMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.SessionAttributeMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.RequestAttributeMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.ServletRequestMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver
 * org.springframework.web.method.annotation.ModelMethodProcessor
 * org.springframework.web.method.annotation.MapMethodProcessor
 * org.springframework.web.method.annotation.ErrorsMethodArgumentResolver
 * org.springframework.web.method.annotation.SessionStatusMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.PrincipalMethodArgumentResolver
 * org.springframework.web.method.annotation.RequestParamMethodArgumentResolver
 * org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 9:55
 * @description
 */
public class ArgumentResolverTest {

    @Test
    public void testCustomArgumentResolver() throws Exception {
        // 创建 Spring boot 中 servlet web 环境容器，在配置类中手动创建 tomcat 实例
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(SpringMvcConfig.class);
        // 从容器中获取 RequestMappingHandlerMapping
        // 该对象用于解析 @RequestMapping 以及派生注解，生成路径与控制器方法的映射关系, 在 web 容器初始化时就生成
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);

        // 模拟的请求
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("POST", "/customArgumentResolver");
        // 设置请求头
        mockRequest.addHeader("token", "this is a token");
        // 模拟的响应
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        // 从映射处理器中，根据请求获取处理链（因为一个请求可能会包含若干个过滤器）
        HandlerExecutionChain chain = handlerMapping.getHandler(mockRequest);
        System.out.println("处理器执行链对象: " + chain);

        // 获取 RequestMappingHandlerAdapter
        MyHandlerAdapter handlerAdapter = context.getBean(MyHandlerAdapter.class);
        // 通过处理器适配器调用相应的控制器方法
        handlerAdapter.invokeHandlerMethod(mockRequest, mockResponse, (HandlerMethod) chain.getHandler());
    }


}
