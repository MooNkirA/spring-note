package com.moon.springmvc.controller;

import com.moon.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共的示例控制层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-27 9:31
 * @description
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    @GetMapping("hello")
    public String hello() {
        System.out.println("DemoController 控制器 hello 方法执行了...");
        return "success";
    }

}
