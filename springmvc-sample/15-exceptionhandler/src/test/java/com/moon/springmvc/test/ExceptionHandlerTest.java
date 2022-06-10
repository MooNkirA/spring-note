package com.moon.springmvc.test;

import com.moon.springmvc.controller.ExceptionHandlerDemoController1;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-10 8:42
 * @description
 */
public class ExceptionHandlerTest {

    // 测试异常处理响应 json
    @Test
    public void test1() throws Exception {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        resolver.afterPropertiesSet();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerMethod handlerMethod = new HandlerMethod(new ExceptionHandlerDemoController1(), ExceptionHandlerDemoController1.class.getMethod("foo"));
        Exception e = new ArithmeticException("被零除");
        resolver.resolveException(request, response, handlerMethod, e);
        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));

    }


}
