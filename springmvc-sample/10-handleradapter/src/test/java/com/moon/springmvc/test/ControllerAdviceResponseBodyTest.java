package com.moon.springmvc.test;

import com.moon.springmvc.common.Constants;
import com.moon.springmvc.controller.ResponseBodyDemoController;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试 @ControllerAdvice 之 ResponseBodyAdvice 返回值增强
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-09 14:29
 * @description
 */
public class ControllerAdviceResponseBodyTest {

    // 模拟 @ControllerAdvice 之 ResponseBodyAdvice 返回值增强
    @Test
    public void test1() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Constants.BASE_PACKAGES);

        ServletInvocableHandlerMethod handlerMethod = new ServletInvocableHandlerMethod(
                context.getBean(ResponseBodyDemoController.class),
                ResponseBodyDemoController.class.getMethod("user")
        );
        handlerMethod.setDataBinderFactory(new ServletRequestDataBinderFactory(Collections.emptyList(), null));
        handlerMethod.setParameterNameDiscoverer(new DefaultParameterNameDiscoverer());
        // 此示例的控制器方法没有入参数，所以可以不用设置参数解析器
        // handlerMethod.setHandlerMethodArgumentResolvers(getArgumentResolvers(context));
        // 添加 advice
        List<ControllerAdviceBean> annotatedBeans = ControllerAdviceBean.findAnnotatedBeans(context);
        List<Object> collect = annotatedBeans.stream()
                .filter(b -> ResponseBodyAdvice.class.isAssignableFrom(b.getBeanType()))
                .collect(Collectors.toList());

        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 此示例只测试 @ResponseBody，所以只加 RequestResponseBodyMethodProcessor 返回值处理器
        composite.addHandler(new RequestResponseBodyMethodProcessor(Arrays.asList(new MappingJackson2HttpMessageConverter()), collect));
        handlerMethod.setHandlerMethodReturnValueHandlers(composite);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ModelAndViewContainer container = new ModelAndViewContainer();
        handlerMethod.invokeAndHandle(new ServletWebRequest(request, response), container);

        System.out.println(new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
    }

}
