package com.moon.springsample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 10:53
 * @description
 */
@Configuration
// 配置包扫描
@ComponentScan("com.moon.springsample")
// 指定导入配置文件
@PropertySource("classpath:name.properties")
public class SpringConfiguration {

    // 注入基本类型
    @Value("red")
    private String color;

    // 注入基本类型，转成int类型
    @Value("123")
    private int number;

    // 使用Spring的el表达式，用于读取配置文件
    @Value("${project.name}")
    private String name;

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
