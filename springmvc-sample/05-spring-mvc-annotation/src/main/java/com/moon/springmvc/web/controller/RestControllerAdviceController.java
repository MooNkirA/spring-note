package com.moon.springmvc.web.controller;

import com.moon.springmvc.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @RestControllerAdvice注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 12:38
 * @description
 */
@Controller
public class RestControllerAdviceController {

    @RequestMapping("/useRestControllerAdvice")
    public void useRestControllerAdvice() {
        throw new CustomException("use @RestControllerAdvice to catch CustomException");
    }

}
