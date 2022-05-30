package com.moon.springmvc.handler;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义 HandlerAdapter（处理器适配器），继承 RequestMappingHandlerAdapter。
 * 其中 RequestMappingHandlerAdapter 类中一些方法是 protected 类型，为了方便测试，
 * 只能通过继承，然后修改其方法的修饰符。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-27 23:25
 * @description
 */
public class MyHandlerAdapter extends RequestMappingHandlerAdapter {

    /**
     * 适配器调用相应请求处理方法。
     * 注：只修改原方法的修饰符，然后直接调用父类中的方法，不作任何更改
     *
     * @param request
     * @param response
     * @param handlerMethod
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView invokeHandlerMethod(HttpServletRequest request, HttpServletResponse response,
                                            HandlerMethod handlerMethod) throws Exception {
        return super.invokeHandlerMethod(request, response, handlerMethod);
    }
}
