package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 注解使用示例控制器 - @SessionAttribute与@SessionAttributes注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-19 16:59
 * @description
 */
@Controller
/*
 * @SessionAttributes注解是用于将数据存入会话域
 *   value或者names属性：指定存入时的名称。本示例只往session域存入“username”、“age”
 *   types属性：指定可以存入会话域中的数据类型。（暂时没有看出来有什么作用）
 */
@SessionAttributes(names = {"username", "age"}, types = {Integer.class}) // 或 @SessionAttributes({"username", "age"})
public class SessionAttributesController {

    /*
     * 往session域中存入数据请求
     *  注：在没有@SessionAttributes注解时，当控制器方法的参数有Model或ModelMap时，是默认往请求域中存入数据
     *  Model是spring提供的一个接口，该接口有一个实现类ExtendedModelMap类继承了ModelMap，而ModelMap就是LinkedHashMap子类
     */
    @RequestMapping("/useSessionAttributes")
    public String useSessionAttributes(Model model) {
        model.addAttribute("username", "石原里美");
        model.addAttribute("password", "123456");
        model.addAttribute("age", "18");
        // 跳转之前将数据保存到username、password和age中，因为注解@SessionAttribute中有这几个参数
        return "success";
    }

    /*
     * 从session域中获取数据请求
     *  @SessionAttribute注解用于从session域中获取数据，value或者name属性获取数据的名称
     *  required属性：用于指定是否必须从会话域中获取到数据。默认值是true，表示如果指定名称不存在会报错
     */
    @RequestMapping("/useSessionAttribute")
    public String useSessionAttribute(@SessionAttribute(value = "username", required = false) String username,
                                      @SessionAttribute(value = "age", required = false) Integer age,
                                      HttpServletRequest request) {
        System.out.println("username: " + username);
        System.out.println("age: " + age);
        System.out.println("request attribute username: " + request.getAttribute("username"));
        System.out.println("request attribute age: " + request.getAttribute("age"));
        return "success";
    }

}
