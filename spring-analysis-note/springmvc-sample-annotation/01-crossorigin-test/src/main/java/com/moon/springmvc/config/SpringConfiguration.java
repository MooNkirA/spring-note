package com.moon.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring核心配置类
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-21 08:41
 * @description
 */
@Configuration
@ComponentScan(value = "com.moon.springmvc",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
public class SpringConfiguration {
}
