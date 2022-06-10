package com.moon.springmvc.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解使用示例控制器 - @RestController注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 12:32
 * @description
 */
/* @RestController注解相当于 @Controller + @ResponseBody，它同时具备以上两个注解的全部功能 */
@RestController
public class RestControllerController {

    @RequestMapping("/useRestController")
    public String useRestController() {
        return "use @RestController to response";
    }

}
