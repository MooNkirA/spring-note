package com.moon.springmvc.test;

import com.moon.springmvc.config.SpringMvcConfig;
import com.moon.springmvc.controller.DefaultHandlerController;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.util.UrlPathHelper;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

/**
 * 默认的返回值处理器测试
 * <p>
 * Spring MVC 内置的参数解析器
 * <p>
 * org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler
 * org.springframework.web.method.annotation.ModelMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.ViewMethodReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBodyReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.CallableMethodReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.DeferredResultMethodReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.AsyncTaskMethodReturnValueHandler
 * org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler
 * org.springframework.web.method.annotation.MapMethodProcessor
 * org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-31 15:14
 * @description
 */
public class DefaultReturnValueHandlerTest {

    private static final Logger log = LoggerFactory.getLogger(DefaultReturnValueHandlerTest.class);
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMvcConfig.class);

    // 测试 ModelAndView 返回类型
    @Test
    public void test1() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test1");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 ModelAndView 类型返回值的处理器
        composite.addHandler(new ModelAndViewMethodReturnValueHandler());
        ServletWebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    // 测试 String 返回类型
    @Test
    public void test2() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test2");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 String 类型返回值的处理器，该类型是指定一个视图名称
        composite.addHandler(new ViewNameMethodReturnValueHandler());
        ServletWebRequest webRequest = new ServletWebRequest(new MockHttpServletRequest(), new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    // 测试 @ModelAttribute 标识对象的返回类型
    @Test
    public void test3() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test3");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 @ModelAttribute 标识对象的返回类型。参数设置方法是否必须标识 @ModelAttribute 注解，false 为必须标识
        composite.addHandler(new ServletModelAttributeMethodProcessor(false));
        MockHttpServletRequest request = new MockHttpServletRequest();
        // 因为测试的方法没有设置 @RequestMapping 注解来指定路径
        request.setRequestURI("/test3");
        // 没有找到视图名，则采用默认以路径为名称
        UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);
        ServletWebRequest webRequest = new ServletWebRequest(request, new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    // 测试无 @ModelAttribute 标识对象的返回类型
    @Test
    public void test4() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test4");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 @ModelAttribute 标识对象的返回类型。参数设置方法是否必须标识 @ModelAttribute 注解，true 为非必须标识
        composite.addHandler(new ServletModelAttributeMethodProcessor(true));
        MockHttpServletRequest request = new MockHttpServletRequest();
        // 因为测试的方法没有设置 @RequestMapping 注解来指定路径
        request.setRequestURI("/test4");
        // 没有找到视图名，则采用默认以路径为名称
        UrlPathHelper.defaultInstance.resolveAndCacheLookupPath(request);
        ServletWebRequest webRequest = new ServletWebRequest(request, new MockHttpServletResponse());
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            renderView(context, container, webRequest); // 渲染视图
        }
    }

    /*
     * **********************************
     * 以下类型都不需要走视图解析与渲染的步骤。
     * 这是因为以下的实现类中的 handleReturnValue 方法，都有其关键代码
     *  mavContainer.setRequestHandled(true);
     * 设置后不会走视图解析流程
     * **********************************
     */
    // 测试 HttpEntity 返回类型
    @Test
    public void test5() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test5");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 HttpEntity 返回类型
        composite.addHandler(new HttpEntityMethodProcessor(Arrays.asList(new MappingJackson2HttpMessageConverter())));
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            if (container.isRequestHandled()) {
                log.debug("response : {}", new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            } else {
                renderView(context, container, webRequest); // 渲染视图
            }
        }
    }

    // 测试 HttpHeaders 返回类型
    @Test
    public void test6() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test6");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 HttpHeaders 返回类型
        composite.addHandler(new HttpHeadersReturnValueHandler());
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            if (container.isRequestHandled()) {
                // 查询所有请求头
                for (String name : response.getHeaderNames()) {
                    log.debug("{} = {}", name, response.getHeader(name));
                }
                log.debug("response : {}", new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            } else {
                renderView(context, container, webRequest); // 渲染视图
            }
        }
    }

    // 测试 @ResponseBody 标识对象的返回类型
    @Test
    public void test7() throws Exception {
        Method method = DefaultHandlerController.class.getMethod("test7");
        DefaultHandlerController controller = new DefaultHandlerController();
        Object returnValue = method.invoke(controller); // 反射获取返回值
        // 将控制对象和方法对象包装成 HandlerMethod
        HandlerMethod methodHandle = new HandlerMethod(controller, method);
        // 初始化 ModelAndViewContainer 容器
        ModelAndViewContainer container = new ModelAndViewContainer();
        HandlerMethodReturnValueHandlerComposite composite = new HandlerMethodReturnValueHandlerComposite();
        // 设置用于解析 @ResponseBody 标识对象的返回类型
        composite.addHandler(new RequestResponseBodyMethodProcessor(Arrays.asList(new MappingJackson2HttpMessageConverter())));
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        ServletWebRequest webRequest = new ServletWebRequest(request, response);
        if (composite.supportsReturnType(methodHandle.getReturnType())) { // 检查是否支持此类型的返回值
            composite.handleReturnValue(returnValue, methodHandle.getReturnType(), container, webRequest);
            log.debug("model : {}", container.getModel());
            log.debug("getViewName : {}", container.getViewName());
            if (container.isRequestHandled()) {
                // 查询所有请求头，这种类型会自动生成 Content-Type = application/json 的请求头
                for (String name : response.getHeaderNames()) {
                    log.debug("{} = {}", name, response.getHeader(name));
                }
                log.debug("response : {}", new String(response.getContentAsByteArray(), StandardCharsets.UTF_8));
            } else {
                renderView(context, container, webRequest); // 渲染视图
            }
        }
    }

    private void renderView(AnnotationConfigApplicationContext context, ModelAndViewContainer container,
                            ServletWebRequest webRequest) throws Exception {
        log.debug(">>>>>> 渲染视图");
        FreeMarkerViewResolver resolver = context.getBean(FreeMarkerViewResolver.class);
        String viewName = container.getViewName() != null ? container.getViewName() : new DefaultRequestToViewNameTranslator().getViewName(webRequest.getRequest());
        log.debug("没有获取到视图名, 采用默认视图名: {}", viewName);
        // 每次渲染时, 会产生新的视图对象, 它并非被 Spring 所管理, 但确实借助了 Spring 容器来执行初始化
        View view = resolver.resolveViewName(viewName, Locale.getDefault());
        view.render(container.getModel(), webRequest.getRequest(), webRequest.getResponse());
        System.out.println(new String(((MockHttpServletResponse) webRequest.getResponse()).getContentAsByteArray(), StandardCharsets.UTF_8));
    }

}
