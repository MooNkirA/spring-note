package com.moon.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-07 16:49
 * @description
 */
@ControllerAdvice
public class ModelAttributeControllerAdvice {

    @ModelAttribute("AA")
    public String foo() {
        return "ModelAttributeControllerAdvice.foo()方法返回值";
    }

}
