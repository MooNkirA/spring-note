package com.moon.springmvc.web.advice;

import com.moon.springmvc.exception.CustomException;
import com.moon.springmvc.web.controller.RestControllerAdviceController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理增强通知
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 00:03
 * @description
 */
@ControllerAdvice
/* @RestControllerAdvice注解相当于 @ControllerAdvice + @ResponseBody，它同时具备以上两个注解的全部功能 */
// @RestControllerAdvice(assignableTypes = {RestControllerAdviceController.class}) // 测试 @RestControllerAdvice 注解专用
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

    // 此异常捕获方法专用于测试 @RestControllerAdvice 注解
    /*@ExceptionHandler(CustomException.class)
    public String handleException(CustomException e) {
        // 直接使用流输出返回错误信息
        return e.getMessage();
    }*/
}
