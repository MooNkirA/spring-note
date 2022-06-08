package com.moon.springmvc.test;

import com.moon.springmvc.config.SpringConfiguration;
import com.moon.springmvc.controller.InitBinderController1;
import com.moon.springmvc.controller.InitBinderController2;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 模拟 @ControllerAdvice 与 @InitBinder 配合实现流程
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-07 16:00
 * @description
 */
public class ControllerAdviceInitBinderTest {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdviceInitBinderTest.class);

    @Test
    public void test() throws Exception {
        /*
            @InitBinder 的来源有两个
            1. @ControllerAdvice 中 @InitBinder 标注的方法，由 RequestMappingHandlerAdapter 在初始化时解析并记录
            2. @Controller 中 @InitBinder 标注的方法，由 RequestMappingHandlerAdapter 会在控制器方法首次执行时解析并记录
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        // 手动创建 RequestMappingHandlerAdapter
        RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter.setApplicationContext(context);
        handlerAdapter.afterPropertiesSet();

        log.debug("1. 初始化...");
        showBindMethods(handlerAdapter); // 打印目前已经绑定的 @InitBinder 标注的方法

        Method getDataBinderFactory = RequestMappingHandlerAdapter.class.getDeclaredMethod("getDataBinderFactory", HandlerMethod.class);
        getDataBinderFactory.setAccessible(true);

        log.debug("2. 模拟调用 InitBinderController1 的 foo 方法时 ...");
        getDataBinderFactory.invoke(handlerAdapter, new HandlerMethod(new InitBinderController1(), InitBinderController1.class.getMethod("foo")));
        showBindMethods(handlerAdapter); // 打印目前已经绑定的 @InitBinder 标注的方法

        log.debug("3. 模拟调用 InitBinderController2 的 bar 方法时 ...");
        getDataBinderFactory.invoke(handlerAdapter, new HandlerMethod(new InitBinderController2(), InitBinderController2.class.getMethod("bar")));
        showBindMethods(handlerAdapter); // 打印目前已经绑定的 @InitBinder 标注的方法

        context.close();
    }

    private void showBindMethods(RequestMappingHandlerAdapter handlerAdapter) throws NoSuchFieldException, IllegalAccessException {
        // 通过反射查看 RequestMappingHandlerAdapter 对象中的 initBinderAdviceCache 属性，该属性是用于缓存 @ControllerAdvice + @InitBinder 注解的解析结果，避免重复解析
        Field initBinderAdviceCache = RequestMappingHandlerAdapter.class.getDeclaredField("initBinderAdviceCache");
        initBinderAdviceCache.setAccessible(true);
        Map<ControllerAdviceBean, Set<Method>> globalMap = (Map<ControllerAdviceBean, Set<Method>>) initBinderAdviceCache.get(handlerAdapter);
        log.debug("全局的 @InitBinder 方法 {}",
                globalMap.values().stream()
                        .flatMap(ms -> ms.stream().map(Method::getName))
                        .collect(Collectors.toList())
        );
        // 通过反射查看 RequestMappingHandlerAdapter 对象中的 initBinderCache 属性，该属性是用于缓存 @InitBinder 注解的解析结果，避免重复解
        Field initBinderCache = RequestMappingHandlerAdapter.class.getDeclaredField("initBinderCache");
        initBinderCache.setAccessible(true);
        Map<Class<?>, Set<Method>> controllerMap = (Map<Class<?>, Set<Method>>) initBinderCache.get(handlerAdapter);
        log.debug("控制器的 @InitBinder 方法 {}",
                controllerMap.entrySet().stream()
                        .flatMap(e -> e.getValue().stream().map(v -> e.getKey().getSimpleName() + "." + v.getName()))
                        .collect(Collectors.toList())
        );
    }

}
