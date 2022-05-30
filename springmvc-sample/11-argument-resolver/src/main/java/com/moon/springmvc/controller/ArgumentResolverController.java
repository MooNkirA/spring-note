package com.moon.springmvc.controller;

import com.moon.springmvc.annotations.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 10:36
 * @description
 */
@RestController
public class ArgumentResolverController {

    @PostMapping("/customArgumentResolver")
    public ModelAndView customArgumentResolver(@Token String token) {
        System.out.println("自定义注解解析参数绑定 token: " + token);
        return null;
    }

}
