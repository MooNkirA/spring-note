package com.moon.springmvc.web.advice;

import com.moon.springmvc.exception.CustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理增强通知
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 00:03
 * @description
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /*
     * @ExceptionHandler注解是用于标识当前方法用于处理控制器执行所产生的异常
     *      value属性是指定哪些异常类型需要捕获
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        String errorMsg;
        // 判断Exception的类型是不是CustomException
        if (e instanceof CustomException) {
            errorMsg = e.getMessage();
        } else {
            // 系统异常
            errorMsg = "服务器内部错误，请联系管理员！";
        }
        // 将错误的信息加到请求域中
        model.addAttribute("errorMsg", errorMsg);
        // 跳转错误页面
        return "error";
    }

}
