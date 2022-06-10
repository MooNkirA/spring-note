package com.moon.springmvc.controller;

import com.moon.springmvc.pojo.User;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-09 11:28
 * @description
 */
public class DemoController {

    @ResponseBody
    public User user() {
        return new User("MoonZero", 22);
    }
}
