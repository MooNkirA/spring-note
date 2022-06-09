package com.moon.springmvc.handler;

import com.moon.springmvc.annotations.Yml;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义返回值解析
 * 示例：解析自定义注解 @Yml，当请求方法上标识此注解后，
 * 将方法的返回值转换成 yml 格式的字条串
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 10:21
 * @description
 */
// Spring MVC 自定义返回值处理器需要实现 HandlerMethodReturnValueHandler 接口
public class CustomReturnValueHandler implements HandlerMethodReturnValueHandler {

    /**
     * 判断是否支持此返回值的类型。示例是判断是否标识了 @Yml 注解
     *
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 判断当前控制器方法上是否有 @Yml 注解
        Yml annotation = returnType.getMethodAnnotation(Yml.class);
        return annotation != null;
    }

    /**
     * 对象返回值进行处理
     *
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 1. 转换返回结果为 yaml 字符串
        String str = new Yaml().dump(returnValue);

        // 2. 将 yaml 字符串写入响应
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().print(str);

        // 3. 设置请求已经处理完毕
        mavContainer.setRequestHandled(true);
    }
}
