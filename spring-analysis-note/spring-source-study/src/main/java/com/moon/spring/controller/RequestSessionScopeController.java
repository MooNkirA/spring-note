package com.moon.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * spring bean的作用范围测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-3 23:55
 * @description
 */
@Controller
@RequestMapping("/scope")
public class RequestSessionScopeController {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/index")
    public void index() {
        System.out.println(applicationContext.getBean("requestBean"));
    }

}
