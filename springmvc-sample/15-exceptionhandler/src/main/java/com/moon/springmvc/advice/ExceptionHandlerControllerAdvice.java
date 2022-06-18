package com.moon.springmvc.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器增强全局异常处理
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-10 10:13
 * @description
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handle(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", e.getMessage());
        return map;
    }

}
