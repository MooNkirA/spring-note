package com.moon.springmvc.controller;

import com.moon.springmvc.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用于测试默认的返回值处理器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-08 14:47
 * @description
 */
public class DefaultHandlerController {

    private static final Logger log = LoggerFactory.getLogger(DefaultHandlerController.class);

    // 测试 ModelAndView 返回类型
    public ModelAndView test1() {
        log.debug("test1()");
        ModelAndView mav = new ModelAndView("view1");
        mav.addObject("name", "张三");
        return mav;
    }

    // 测试 String 返回类型
    public String test2() {
        log.debug("test2()");
        return "view2";
    }

    // 测试 @ModelAttribute 标识对象的返回类型
    @ModelAttribute
    // @RequestMapping("/test3")
    public User test3() {
        log.debug("test3()");
        return new User("李四", 20);
    }

    // 测试无 @ModelAttribute 标识对象的返回类型
    public User test4() {
        log.debug("test4()");
        return new User("王五", 30);
    }

    /*
     * **********************************
     * 以下类型都不需要走视图解析与渲染的步骤
     * **********************************
     */
    // 测试 HttpEntity 返回类型
    public HttpEntity<User> test5() {
        log.debug("test5()");
        return new HttpEntity<>(new User("赵六", 40));
    }

    // 测试 HttpHeaders 返回类型
    public HttpHeaders test6() {
        log.debug("test6()");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        return headers;
    }

    // 测试 @ResponseBody 标识对象的返回类型
    @ResponseBody
    public User test7() {
        log.debug("test7()");
        return new User("钱七", 50);
    }

}
