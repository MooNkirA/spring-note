package com.moon.springmvc.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 测试的全局
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-07 16:04
 * @description
 */
@ControllerAdvice
public class InitBinderControllerAdvice {

    @InitBinder
    public void binder(WebDataBinder webDataBinder) {
        System.out.println("InitBinderControllerAdvice.binder 转换器方法执行了...");
    }

}
