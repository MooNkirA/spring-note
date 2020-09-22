package com.moon.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义拦截器使用示例控制器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-22 14:09
 * @description
 */
@Controller
public class CustomInterceptorController {

    /**
     * 测试自定义拦截拦截器请求
     */
    @RequestMapping("/testCustomInterceptor")
    public String testCustomInterceptor(ModelMap model) {
        System.out.println("testCustomInterceptor方法执行了");
        // 设置message信息
        model.addAttribute("message", "Hello MooNkirA, xxoo!");
        return "message";
    }

    /**
     * 模拟登录方法
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        // 往会话域中设置loginName属性
        request.getSession().setAttribute("loginName", "test");
        // 使用关键字“forward”，进行页面转发。此时会脱离web项目配置的视图解析器，直接找index.jsp
        return "forward:/index.jsp";
    }
}
