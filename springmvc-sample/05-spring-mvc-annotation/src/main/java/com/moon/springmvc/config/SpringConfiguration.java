package com.moon.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring核心配置类，用于替代applicationContext.xml配置文件
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-17 14:36
 * @description
 */
@Configuration // 标识为配置类
/*
 * 配置包扫描
 * 因为包扫描的范围较大，将控制层的注解也扫描了，为了让此配置类专注于替代原spring的applicationContext.xml配置文件
 * 所以使用excludeFilters属性，排除不扫描@Controller等注解，在专门的SpringMVC的配置类中扫描即可
 */
@ComponentScan(value = "com.moon.springmvc",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
public class SpringConfiguration {
}
