package com.moon.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring核心配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-27 9:45
 * @description
 */
@Configuration
/*
 * 配置包扫描，排除控制层注解扫描
 * 因为包扫描的范围较大，将控制层的注解也扫描了，为了让此配置类专注于替代原spring的applicationContext.xml配置文件
 * 所以使用excludeFilters属性，排除不扫描@Controller等注解，在专门的 SpringMVC 的配置类中扫描即可
 */
@ComponentScan("com.moon.springmvc")
// @ComponentScan(value = "com.moon.springmvc",
//         excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, RestController.class}))
public class SpringConfiguration {
}
