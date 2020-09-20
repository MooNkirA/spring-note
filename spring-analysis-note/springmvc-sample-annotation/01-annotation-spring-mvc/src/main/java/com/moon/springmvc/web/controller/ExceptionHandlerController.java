package com.moon.springmvc.web.controller;

import com.moon.springmvc.exception.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @ExceptionHandler注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 00:09
 * @description
 */
@Controller
public class ExceptionHandlerController {

    @RequestMapping("/useExceptionHandler")
    public String useExceptionHandler(Integer age) {
        if (age == null) {
            throw new NullPointerException();
        }
        if (age > 120) {
            throw new CustomException("年龄不合法!");
        }
        System.out.println("年龄是：" + age);
        return "success";
    }

}
