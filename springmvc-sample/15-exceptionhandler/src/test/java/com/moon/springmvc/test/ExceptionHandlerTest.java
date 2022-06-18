package com.moon.springmvc.test;

import com.moon.springmvc.config.SpringMvcConfig;
import com.moon.springmvc.controller.ExceptionHandlerDemoController1;
import com.moon.springmvc.controller.ExceptionHandlerDemoController2;
import com.moon.springmvc.controller.ExceptionHandlerDemoController3;
import com.moon.springmvc.controller.ExceptionHandlerDemoController4;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-10 8:42
 * @description
 */
public class ExceptionHandlerTest {

    private final ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();

    @Before
    public void init() {
        resolver.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        resolver.afterPropertiesSet();
    }

    // 测试异常处理时响应 json
    @Test
    public void test1() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerMethod handlerMethod = new HandlerMethod(new ExceptionHandlerDemoController1(), ExceptionHandlerDemoController1.class.getMethod("foo"));
        Exception e = new ArithmeticException("除数不能为零");
        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    // 测试异常处理时响应 ModelAndView
    @Test
    public void test2() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerMethod handlerMethod = new HandlerMethod(new ExceptionHandlerDemoController2(), ExceptionHandlerDemoController2.class.getMethod("foo"));
        Exception e = new ArithmeticException("除数不能为零");
        ModelAndView modelAndView = resolver.resolveException(request, response, handlerMethod, e);
        System.out.println("model: " + modelAndView.getModel());
        System.out.println("viewName: " + modelAndView.getViewName());
    }

    // 测试嵌套异常处理时响应 json
    @Test
    public void test3() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerMethod handlerMethod = new HandlerMethod(new ExceptionHandlerDemoController1(), ExceptionHandlerDemoController1.class.getMethod("foo"));
        // 嵌套的异常，多个异常必须有一个
        Exception e = new Exception("嵌套的异常1", new RuntimeException("嵌套的异常2", new ArithmeticException("嵌套的异常3")));
        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    // 测试异常处理方法的参数解析
    @Test
    public void test4() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerMethod handlerMethod = new HandlerMethod(new ExceptionHandlerDemoController3(), ExceptionHandlerDemoController3.class.getMethod("foo"));
        Exception e = new Exception("this is an exception");
        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

    // 测试
    @Test
    public void test5() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMvcConfig.class);
        ExceptionHandlerExceptionResolver resolver = context.getBean(ExceptionHandlerExceptionResolver.class);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerMethod handlerMethod = new HandlerMethod(new ExceptionHandlerDemoController4(), ExceptionHandlerDemoController4.class.getMethod("foo"));
        Exception e = new Exception("look! Exception!");
        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }


}
