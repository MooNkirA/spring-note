package com.moon.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * spring bean的 request与Session 作用范围测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-15 17:15
 * @description
 */
// @Controller
// @RequestMapping("/scope")
public class RequestSessionScopeController {

    @Autowired
    ApplicationContext applicationContext;

    // @RequestMapping("/index")
    public void index() {
        System.out.println(applicationContext.getBean("requestBean"));
    }

}
