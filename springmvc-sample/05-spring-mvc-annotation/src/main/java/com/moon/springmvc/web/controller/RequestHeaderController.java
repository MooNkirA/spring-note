package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @RequestHeader注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 23:34
 * @description
 */
@Controller
public class RequestHeaderController {

    @RequestMapping("/useRequestHeader")
    /*
     * @RequestHeader 注解是从请求消息头中获取消息头的值，并把值赋给控制器方法形参
     *  注：只能用于方法形参上
     *  value属性与name属性：用于指定请求消息头的名称
     *  required属性：用于指定是否必须有此消息头。默认值为true，表示必须有此消息头，否则请求报错
     *  defaultValue属性：用于指定请求没此消息头时，给相应的方法形参设置此默认值
     */
    public String useRequestHeader(@RequestHeader(value = "Accept-Language", required = false, defaultValue = "test") String header) {
        System.out.println("Accept-Language:" + header);
        return "success";
    }

}
