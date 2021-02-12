package com.moon.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 14:09
 * @description
 */
@Configuration
// @PropertySource注解只是引入配置文件，无法实现将注入属性值替换相应占位符的值
@PropertySource("classpath:application.properties")
public class SpringConfiguration {
}
