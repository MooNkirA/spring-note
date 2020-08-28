package com.moon.springsample.utils;

import org.springframework.stereotype.Component;

/**
 * 模拟一个日志工具类，主要是用于测试 @Component 注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-25 23:04
 * @description
 */
@Component
public class LogUtil {

    public void printLog() {
        System.out.println("LogUtil.printLog()方法输出日志");
    }

}
