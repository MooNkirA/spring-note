package com.moon.springmvc.web.controller;

import com.moon.springmvc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 注解使用示例控制器 - @RequestParam注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 13:45
 * @description
 */
@Controller
public class RequestParamController {

    /*
     * 基本类型和String类型的封装
     *  请求参数的名称与方法形参的名称一致即可
     */
    @RequestMapping("/useParam1")
    public String param1(String name, Integer age) {
        System.out.println("name is " + name + ", age is " + age);
        return "success";
    }

    /*
     * 实体类类型的封装
     *  注：只要实体类中的setXxx方法名称，去掉set后部分名称与请求参数的名称一致即可封装成功
     *      与属性的名称无关
     */
    @RequestMapping(value = "/useParam2")
    public String param2(User user) {
        System.out.println("user is " + user);
        return "success";
    }

    /*
     * @RequestParam注解的使用示例
     *  value与name（4.2版本引入）属性均为指定获取请求参数的名称，用于请求参数与方法名称不一致的情况
     *  required属性：指定参数是否必须有值，默认值为true。当为true时，参数没有值时会报错
     *  defaultValue属性：在参数没有值时的默认值
     */
    @RequestMapping("/useRequestParam")
    public String useRequestParam(@RequestParam(name = "currentPage", defaultValue = "10", required = false) int page) {
        System.out.println("当前页是：" + page);
        return "success";
    }

}
