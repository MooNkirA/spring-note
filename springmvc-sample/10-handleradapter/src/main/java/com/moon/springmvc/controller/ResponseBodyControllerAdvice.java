package com.moon.springmvc.controller;

import com.moon.springmvc.utils.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对 @ResponseBody 是返回值进行增强
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-09 14:40
 * @description
 */
@ControllerAdvice
public class ResponseBodyControllerAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 用于指定满足哪些条件才进行转换
     *
     * @param returnType    返回的类型
     * @param converterType 选定转换器的类型
     * @return 如返回 true 则代表可以进行转换，否则返回 false
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        /*
         * 这里需要判断三种情况
         *      1. 在控制器方法上标识 @ResponseBody 注解
         *      2. 在控制器类上标识 @ResponseBody 注解  可以使用 returnType.getContainingClass().isAnnotationPresent(ResponseBody.class) 来判断
         *      3. 在控制器类上标识 @RestController 注解
         * 其中 Spring 提供的 AnnotationUtils 工具类的 findAnnotation 方法，可以判断类上或者类上组合注解是否包含某个注解
         */
        if (returnType.getMethodAnnotation(ResponseBody.class) != null ||
                AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null) {
            return true;
        }
        return false;
    }

    /**
     * 响应体转换处理逻辑
     *
     * @param body                  响应数据
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            // 如果是 Result 统一响应类型，则直接返回
            return body;
        }
        return Result.ok(body);
    }
}
