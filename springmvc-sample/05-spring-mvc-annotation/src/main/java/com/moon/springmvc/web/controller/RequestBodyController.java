package com.moon.springmvc.web.controller;

import com.moon.springmvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @RequestBody注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-20 08:57
 * @description
 */
@Controller
public class RequestBodyController {

    // @RequestMapping("/useRequestBody")
    /*
     * Spring MVC默认能绑定到方法形参的请求类型为
     *   “Content-Type: application/form-data”或者“Content-Type: application/x-www-form-urlencoded”
     * 如果数据是在请求体中“`Content-Type: application/json`”，则无法绑定到方法形参中
     * @RequestBody注解用于获取全部的请求体数据
     *   required属性：用于指定是否必须有请求体，默认值为true，请求中必须有请求体
     */
    /*public String useRequestBody(@RequestBody(required = false) String user) {
        System.out.println("user is " + user);
        return "success";
    }*/

    /*
     * @RequestBody注解没有实现将json数据封装到实体类中的功能，需要借助第三JSON处理类库或者自定义实现
     *  此示例引入jackson作为json数据处理框架，详情配置参考pom.xml与SpringMvcConfiguration
     */
    @RequestMapping("/useRequestBody")
    public String useRequestBody(@RequestBody User user) {
        System.out.println("user is " + user);
        return "success";
    }

}
