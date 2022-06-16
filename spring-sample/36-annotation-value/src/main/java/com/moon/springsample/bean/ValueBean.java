package com.moon.springsample.bean;

import com.moon.springsample.config.SpringConfiguration;
import org.springframework.beans.factory.annotation.Value;

/**
 * 测试类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-15 14:22
 * @description
 */
public class ValueBean {

    @Value("${JAVA_HOME}")
    private String home;
    @Value("18")
    private int age;

    // 使用 spEl 表达式(全称 SpringEL)
    @Value("#{@springConfiguration}")
    private SpringConfiguration config;

    // 使用
    @Value("#{'hello, ' + '${JAVA_HOME}'}")
    private String value;

}
