package com.moon.springmvc.web.exceptionresolver;

import com.moon.springmvc.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理解析器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-22 17:11
 * @description
 */
// @Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * 此方法是处理异常的。异常就分为系统异常和业务异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 1. 创建返回值对象
        ModelAndView mv = new ModelAndView();
        // 2. 设置错误提示信息
        String errorMsg;
        if (ex instanceof CustomException) {
            errorMsg = ex.getMessage();
        } else {
            // 系统异常
            errorMsg = "服务器内部错误，请联系管理员！";
        }
        mv.addObject("errorMsg", errorMsg);
        // 3. 设置结果视图名称
        mv.setViewName("error");
        // 4. 返回
        return mv;
    }

}
