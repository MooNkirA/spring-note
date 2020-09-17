package com.moonzero.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring框架配置类
 *
 * @author MoonZero
 */
// 使用注解设置为配置类
@Configuration
// 使用注解开启扫描
// @ComponentScan(basePackages = "com.moonzero")
//查看源码，basePackages有别名为value并且参数值只有一个，所以可以省略
@ComponentScan("com.moonzero")
public class SpringConfiguration {

}
