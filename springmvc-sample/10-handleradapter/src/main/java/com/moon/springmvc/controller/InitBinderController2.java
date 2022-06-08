package com.moon.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 测试控制器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-07 16:05
 * @description
 */
@Controller
public class InitBinderController2 {

    @InitBinder
    public void binder1(WebDataBinder webDataBinder) {
        System.out.println("InitBinderController2.binder1 转换器方法执行了...");
    }

    @InitBinder
    public void binder2(WebDataBinder webDataBinder) {
        System.out.println("InitBinderController2.binder2 转换器方法执行了...");
    }

    public void bar() {
    }

}
