package com.moon.springmvc.resolver;

import com.moon.springmvc.annotations.Token;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义参数解析
 * 示例：解析自定义注解 @Token，当请求方法参数标识此注解后，
 * 从请求头中 token 参数数中获取相应的值
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 10:21
 * @description
 */
// Spring MVC 自定义参数解析需要实现 HandlerMethodArgumentResolver 接口
public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断是否支持当前参数
     *
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 判断当前请求的方法参数是否有 @Token 注解
        Token annotation = parameter.getParameterAnnotation(Token.class);
        return annotation != null;
    }

    /**
     * 参数解析处理
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 获取请求头中指定参数的值
        return webRequest.getHeader("token");
    }

}
