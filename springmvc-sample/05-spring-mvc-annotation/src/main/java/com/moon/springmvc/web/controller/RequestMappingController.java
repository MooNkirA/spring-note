package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @RequestMapping注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 09:44
 * @description
 */
@Controller
/*
 * @RequestMapping标识在类上，代表url的一级访问目录
 * 目的是为了使URL可以按照模块化管理
 */
@RequestMapping("springmvc")
public class RequestMappingController {

    /**
     * @RequestMapping标识在方法上，代表url的二级访问目录
     */
    /*
     * value属性，用于指定请求的URL。如果只有value属性时，可省略不写属性名
     * value属性与path属性作用一样，path属性是4.2版本中加入
     * 注意细节：
     *      1. 在配置value属性时，写不写"/"效果都是一样
     *      2. 配置多个属性（name属性除外）时，它们之间是”与“的关系，即如果有一个属性不符合，则都不能请求成功
     */
    // @RequestMapping("/useRequestMapping")
    // @RequestMapping(path = "useRequestMapping")

    /*
     * name属性：用于标识当前请求URL，在使用AOP时，可以通过获取注解的name属性值来判断需要增加的方法
     * method属性：用于指定请求的方式，指定后必须符合此请求方式才能成功调用到此方法
     */
    // @RequestMapping(value = "useRequestMapping", name = "使用RequestMapping注解的请求映射",
    //         method = RequestMethod.POST)

    /*
     * params属性：指定限制请求参数的条件。示例表示请求时必须要有name属性，否则不能请求成功
     * headers属性：用于指定限制请求消息头的条件。示例表示请求时请求头必须要有Accept-Encoding与custom-auth属性，否则不能请求成功
     */
    // @RequestMapping(value = "useRequestMapping", method = RequestMethod.GET, params = {"name"},
    //         headers = {"Accept-Encoding", "custom-auth"})

    /*
     * @RequestMapping的衍生注解：
     *      @GetMapping、@PostMapping、@PutMapping、@DeleteMapping、@PatchMapping
     *      其衍生注解的属性与@RequestMapping一样，只是将请求方式限定了，即没有与不用设置method属性
     */
    @PostMapping(value = "useRequestMapping", params = {"name"}, headers = {"Accept-Encoding"})
    public String useRequestMapping() {
        System.out.println("RequestMappingController.useRequestMapping()方法执行了...");
        return "success";
    }

}
