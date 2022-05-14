package com.moon.springsample.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟日志工具类，本示例用于测试 @Lazy 注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-31 22:33
 * @description
 */
@Component
// 指定单例bean延迟加载，不是在容器创建时就创建当前bean对象，在bean对象调用的时候才创建
@Lazy
// 如果指定当前多例模式，则@Lazy没有任何作用，因为多例情况下，bean实例都是懒加载的。
// @Scope("prototype")
public class LogUtil {

    public LogUtil() {
        System.out.println("LogUtil构造函数执行了");
    }

    public void printLog() {
        System.out.println("LogUtil.printLog()方法调用，模拟记录日志");
    }

}
