package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入门案例的基础控制器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-17 14:27
 * @description
 */
// 标识当前类为控制层
@Controller
public class BasicController {

    // 配置请求url
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("BasicController控制器sayHello()方法执行了...");
        return "success";
    }

}
