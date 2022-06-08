package com.moon.springmvc.controller;

import com.moon.springmvc.pojo.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-07 16:51
 * @description
 */
public class ModelAttributeController {

    @ModelAttribute("BB")
    public String foo() {
        return "ModelAttributeController.foo()方法返回值";
    }

    @ResponseStatus(HttpStatus.OK)
    public ModelAndView bar(@ModelAttribute("u") User user) {
        System.out.println("ModelAttributeController.bar 方法执行了...");
        return null;
    }

}
