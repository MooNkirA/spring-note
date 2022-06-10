package com.moon.spring.config;

import com.moon.spring.constant.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Spring 项目配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-14 16:42
 * @description
 */
@ComponentScan(Constants.BASE_PACKAGES)

// 使用数组的形式引入多个配置文件
// @PropertySource({"classpath:application.properties", "classpath:app.properties"})

// 使用多个@PropertySource引入多个配置文件
// @PropertySource("classpath:application.properties")
// @PropertySource("classpath:app.properties")

// 使用@PropertySources引入多个@PropertySource
@PropertySources({@PropertySource("classpath:application.properties"), @PropertySource("classpath:app.properties")})
public class SpringConfiguration {
}
