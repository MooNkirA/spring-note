package com.moon.springmvc.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，纯注解开发中自定义拦截器步骤：
 * 第一步：编写一个普通类，实现HandlerInterceptor接口
 * 第二步：使用注解，把拦截器存入IOC容器
 * 第三步：修改配置类，将拦截器注册到 InterceptorRegistry 中
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-22 13:44
 * @description
 */
@Component
public class MyInterceptor1 implements HandlerInterceptor {

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
        System.out.println("MyInterceptor1.preHandle()方法执行了....");
        // 示例需求：如果是登陆请求，直接放行；如果会话域存在“loginName”属性，则放行，否拦截
        if (request.getRequestURL().toString().contains("login")) {
            // 登录方法直接放行
            return true;
        }
        Object obj = request.getSession().getAttribute("loginName");
        // 判断“loginName”属性是否有值，不为空则放行，否则不放行
        return obj != null;
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
        System.out.println("MyInterceptor1.postHandle()方法执行了....");
        Assert.notNull(modelAndView, "modelAndView must be not null!");
        Object object = modelAndView.getModelMap().get("message");
        if (object != null) {
            String message = (String) object;
            message = message.replace("xxoo", "**");
            modelAndView.getModelMap().addAttribute("message", message);
        }
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
        System.out.println("MyInterceptor1.afterCompletion()方法执行了....");
    }
}
