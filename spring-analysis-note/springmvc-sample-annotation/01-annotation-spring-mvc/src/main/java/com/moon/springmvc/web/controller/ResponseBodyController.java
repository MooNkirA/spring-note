package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注解使用示例控制器 - @ResponseBody注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 11:54
 * @description
 */
@Controller
/* @ResponseBody标识在类上，代表当前类所有方法都使用流输出响应正文（数据） */
@ResponseBody
public class ResponseBodyController {

    @RequestMapping("/useRequestBodyOnMethod")
    /* @ResponseBody标识在方法返回值前，代表当前方法使用流输出响应正文（数据） */
    @ResponseBody
    public String useRequestBodyOnMethod() {
        return "success! use @ResponseBody at method";
    }

    @RequestMapping("/useResponseBodyOnClass")
    public String useResponseBodyOnClass() {
        return "use @ResponseBody at class";
    }

}
