package com.moon.springmvc.controller;

import com.moon.springmvc.annotations.Token;
import com.moon.springmvc.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-29 10:36
 * @description
 */
@RestController
public class ArgumentResolverController {

    @PostMapping("/customArgumentResolver")
    public ModelAndView customArgumentResolver(@Token String token) {
        System.out.println("自定义注解解析参数绑定 token: " + token);
        return null;
    }

    // 用于测试不同数据类型解析，为了方便，都写到一个请求方法中
    @PostMapping("/test/{id}")
    public String testArgumentType(
            @RequestParam("name1") String name1, // name1=张三
            String name2,                        // name2=李四
            @RequestParam("age") int age,        // age=18
            @RequestParam(name = "home", defaultValue = "${JAVA_HOME}") String home1, // spring 从环境对象中获取数据
            @RequestParam("file") MultipartFile file, // 上传文件
            @PathVariable("id") int id,               // 请求路径匹配 /test/{id} ---> /test/124
            @RequestHeader("Content-Type") String header, // 从请求头获取
            @CookieValue("token") String token,  // 从 cookie 中获取
            @Value("${JAVA_HOME}") String home2, // 从 spring 容器中获取数据  ${} #{}
            HttpServletRequest request,          // request, response, session ...
            @ModelAttribute("abc") User user1,   // name=zhang&age=18
            User user2,                          // name=zhang&age=18
            @RequestBody User user3              // 从请求体中 json 获取
    ) {
        return "success";
    }

}
