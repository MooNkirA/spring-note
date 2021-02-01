package com.moon.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 此示例项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-31 11:45
 * @description
 */
@PropertySource("classpath:application.properties") // 引入自定义配置文件
@ComponentScan("com.moon.spring") // 包扫描
@Configuration
public class SpringConfiguration {
}
