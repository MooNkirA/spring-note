package com.moon.springsample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * spring项目的配置类，用于代替传统的xml配置文件
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-29 21:43
 * @description
 */
@Configuration /* 标识当前类为配置类 */
@Import(JdbcConfig.class) /* @Import注解是写在类上的，通常是和注解驱动的配置类一起使用的。其作用是引入其他的配置类 */
@PropertySource("classpath:jdbc.properties") /* 用于指定读取资源文件的位置。不仅支持properties，也支持xml文件 */
public class SpringConfiguration {
}
