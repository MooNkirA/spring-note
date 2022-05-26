package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @CookieValue注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-19 09:35
 * @description
 */
@Controller
public class CookieValueController {

    @RequestMapping("/useCookieValue")
    /*
     * @CookieValue 注解是从请求消息头中获取Cookie的值，并把值赋给控制器方法形参
     *  注：只能用于方法形参上
     *  value属性与name属性：用于指定请求cookie的名称
     *  required属性：用于指定是否必须有此cookie。默认值为true，表示必须有此cookie，否则请求报错
     *  defaultValue属性：用于指定请求没此cookie时，给相应的方法形参设置此默认值
     */
    public String useCookieValue(@CookieValue(value = "springmvc_sample", required = false,
            defaultValue = "give me defaultValue!") String myCookie) {
        System.out.println(myCookie);
        return "success";
    }

}
