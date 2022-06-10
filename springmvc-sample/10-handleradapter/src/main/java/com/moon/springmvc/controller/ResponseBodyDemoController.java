package com.moon.springmvc.controller;

import com.moon.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共的示例控制层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-27 9:31
 * @description
 */
@RestController
// @Controller
// @ResponseBody
public class ResponseBodyDemoController {

    // @ResponseBody
    public User user() {
        return new User("王五", 18);
    }

}
