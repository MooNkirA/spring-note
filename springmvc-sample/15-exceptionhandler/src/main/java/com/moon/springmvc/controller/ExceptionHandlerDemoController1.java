package com.moon.springmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * ExceptionHandler 注解测试控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-09 23:23
 * @description
 */
public class ExceptionHandlerDemoController1 {

    public void foo() {
    }

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handle(ArithmeticException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        return map;
    }

}
