package com.moon.springmvc.web.controller;

import com.moon.springmvc.annotation.CustomAnnotation;
import com.moon.springmvc.domain.User;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解使用示例控制器 - @InitBinder注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 15:15
 * @description
 */
@Controller
@CustomAnnotation
public class InitBinderController {

    /*
     * @InitBinder注解的使用示例
     */
    @RequestMapping("/useInitBinder")
    public String useInitBinder(User user) {
        System.out.println("user is " + user);
        return "success";
    }

    /*
     * 通过@InitBinder初始化请求参数的数据绑定器
     *  注解只有value属性：用于指定给哪些参数进行绑定操作
     *  如果什么都不指定，只会将当前控制器类中所有方法的形参实体类进行初始化绑定
     *
     * 注意点：在某个Controller控制类中使用了`@InitBinder`注解进行数据转换绑定，只能对当前控制类生效，其他的控制类是不起作用
     */
    // @InitBinder
    /*@InitBinder({"user", "otherParams"})
    public void initBinder(WebDataBinder webDataBinder) {
        // 初始化请求参数中日期字符串转成日期Date对象
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }*/

}
