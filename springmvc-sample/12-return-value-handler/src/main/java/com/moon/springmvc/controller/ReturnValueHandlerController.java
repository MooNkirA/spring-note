package com.moon.springmvc.controller;

import com.moon.springmvc.annotations.Yml;
import com.moon.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义返回值处理器测试控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-31 15:16
 * @description
 */
@Controller
public class ReturnValueHandlerController {

    @RequestMapping("/customReturnValueHandler")
    @Yml // 使用自定义注解，测试对返回值处理
    public User customReturnValueHandler(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

}
