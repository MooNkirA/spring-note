package com.moon.springmvc.controller;

import com.moon.springmvc.formatter.MyDateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 测试用 @InitBinder 实现数据转换
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-06 16:16
 * @description
 */
public class InitBinderController {

    /**
     * 在某个 Controller 控制类中使用了 @InitBinder 注解进行数据转换绑定，
     * 只能对当前控制类生效，其他的控制类是不起作用
     *
     * @param dataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // 通过 @InitBinder 方式扩展 dataBinder 的转换器
        dataBinder.addCustomFormatter(new MyDateFormatter("用 @InitBinder 方式扩展的"));
    }

}
