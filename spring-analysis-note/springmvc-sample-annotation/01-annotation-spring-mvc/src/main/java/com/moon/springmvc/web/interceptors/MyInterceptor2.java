package com.moon.springmvc.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器2，用于测试多个拦截器时的执行顺序
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-22 13:44
 * @description
 */
@Component
public class MyInterceptor2 implements HandlerInterceptor {

    /**
     * 拦截器的拦截方法，它是在控制器方法执行之前先执行。可以做一些前置增强
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor2.preHandle()方法执行了....");
        return true;
    }

    /**
     * 拦截器的后处理方法，执行时机在控制器方法执行之后，同时结果视图执行之前。可以用于对响应数据进行增强
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor2.postHandle()方法执行了....");
    }

    /**
     * 拦截器最后执行的方法，执行时机在结果视图执行完成之后，响应之前。可以用于实现一些清理的操作
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor2.afterCompletion()方法执行了....");
    }
}
