package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @ModelAttribute注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-19 10:53
 * @description
 */
@Controller
public class ModelAttributeController {

    /*
     * @ModelAttribute修饰方法时，表示执行控制器所有方法之前，先执行此注解修饰的方法
     *  获取@ModelAttribute修饰方法时对请求参数的处理值方式1：
     *      1. 在方法形参上增加Modal对象，
     *      2. 将处理的参数设置到Model对象中，对象的key-value的map结构
     */
    /*@ModelAttribute
    public void showModel(String name, Model model) {
        System.out.println("showModel method name is " + name);
        name = "[" + name + "]";
        model.addAttribute("username", name);
    }*/

    /*
     * @ModelAttribute修饰方法时，表示执行控制器所有方法之前，先执行此注解修饰的方法
     *  获取@ModelAttribute修饰方法时对请求参数的处理值方式2：
     *      1. @ModelAttribute设置value或name属性值，该值为存入Model对象时的key
     *      2. 然后在方法中返回需要存入的值
     */
    @ModelAttribute("username")
    public String showModel(String name) {
        System.out.println("showModel method name is " + name);
        return "[" + name + "]";
    }

    @RequestMapping("/useModelAttribute")
    /*
     * @ModelAttribute修饰方式形参时，用于获取指定的数据给参数赋值
     *  无论使用方式1或者方式2，都可以通过@ModelAttribute注解指定存入时的key，从而绑定处理后的值
     */
    public String useModelAttribute(@ModelAttribute("username") String name) {
        System.out.println("controller method name is " + name);
        return "success";
    }

}
