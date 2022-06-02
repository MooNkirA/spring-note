package com.moon.springmvc.test;

import com.moon.springmvc.config.SpringMvcConfig;
import com.moon.springmvc.controller.ArgumentResolverController;
import com.moon.springmvc.handler.MyHandlerAdapter;
import com.moon.springmvc.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExpressionValueMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletCookieValueMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestMethodArgumentResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    // 自定义参数解析器测试
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

    // 测试 Spring MVC 默认的参数解析器
    /*
       RequestMappingHandlerAdapter 的调用过程
           1. 控制器方法被封装为 HandlerMethod
           2. 准备对象绑定与类型转换
           3. 准备 ModelAndViewContainer 用来存储中间 Model 结果
           4. 解析每个参数值
        解析参数依赖的就是各种参数解析器，它们都有两个重要方法
           - supportsParameter 判断是否支持方法参数
           - resolveArgument 解析方法参数
    */
    @Test
    public void testDefaultArgumentResolver() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringMvcConfig.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        // 准备模拟的请求对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("name1", "zhangsan");
        request.setParameter("name2", "lisi");
        request.addPart(new MockPart("file", "abc", "hello".getBytes(StandardCharsets.UTF_8)));
        Map<String, String> map = new AntPathMatcher().extractUriTemplateVariables("/test/{id}", "/test/123");
        System.out.println(map);
        request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, map);
        request.setContentType("application/json");
        request.setCookies(new Cookie("token", "123456"));
        request.setParameter("name", "张三");
        request.setParameter("age", "18");
        request.setContent("{\"name\":\"李四\",\"age\":20}".getBytes(StandardCharsets.UTF_8));

        // 控制器方法被封装 HandlerMethod
        HandlerMethod handlerMethod = new HandlerMethod(new ArgumentResolverController(),
                ArgumentResolverController.class.getMethod("testArgumentType",
                        String.class, String.class, int.class, String.class,
                        MultipartFile.class, int.class, String.class, String.class,
                        String.class, HttpServletRequest.class, User.class, User.class, User.class)
        );

        // 准备对象绑定与类型转换
        ServletRequestDataBinderFactory factory = new ServletRequestDataBinderFactory(null, null);

        // 准备 ModelAndViewContainer 用来存储中间 Model 结果
        ModelAndViewContainer container = new ModelAndViewContainer();

        //  解析控制器方法上每个参数值
        for (MethodParameter parameter : handlerMethod.getMethodParameters()) {
            // 多个解析器组合
            HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
            composite.addResolvers(
                    // 第二个参数，如果为 false 表示必须有 @RequestParam
                    new RequestParamMethodArgumentResolver(beanFactory, true),
                    new PathVariableMethodArgumentResolver(),
                    new RequestHeaderMethodArgumentResolver(beanFactory),
                    new ServletCookieValueMethodArgumentResolver(beanFactory),
                    new ExpressionValueMethodArgumentResolver(beanFactory),
                    new ServletRequestMethodArgumentResolver(),
                    new ServletModelAttributeMethodProcessor(false), // 必须有 @ModelAttribute
                    new RequestResponseBodyMethodProcessor(Arrays.asList(new MappingJackson2HttpMessageConverter())),
                    new ServletModelAttributeMethodProcessor(true), // 省略了 @ModelAttribute
                    new RequestParamMethodArgumentResolver(beanFactory, true) // 省略 @RequestParam
            );

            String annotations = Arrays.stream(parameter.getParameterAnnotations()).map(a -> a.annotationType().getSimpleName()).collect(Collectors.joining());
            String str = annotations.length() > 0 ? " @" + annotations + " " : " ";
            parameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());

            // supportsParameter 方法，判断解析器是否支持解析当前参数
            if (composite.supportsParameter(parameter)) {
                // 支持此参数，resolveArgument 解析方法参数
                Object v = composite.resolveArgument(parameter, container, new ServletWebRequest(request), factory);
                // System.out.println(v.getClass());
                System.out.println("[" + parameter.getParameterIndex() + "] " + str + parameter.getParameterType().getSimpleName() + " " + parameter.getParameterName() + "->" + v);
                System.out.println("模型数据为：" + container.getModel());
            } else {
                System.out.println("[" + parameter.getParameterIndex() + "] " + str + parameter.getParameterType().getSimpleName() + " " + parameter.getParameterName());
            }
        }
    }

}
