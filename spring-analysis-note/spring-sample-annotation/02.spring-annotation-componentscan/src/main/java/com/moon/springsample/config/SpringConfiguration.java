package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-31 23:33
 * @description
 */
@Configuration /* 标识当前类为配置类 */
// @ComponentScan /* 配置开启包扫描，不写扫描的包路径，则默认扫描当前@ComponentScan注解的类所在的包及其下的所有子包 */
// @ComponentScan("com.moon.springsample") /* 配置开启包扫描，配置value属性，如果没有配置，则可以省略不写value="xxxx" */
@ComponentScan(basePackages = {"com.moon.springsample"}) /* 配置开启包扫描，配置basePackages属性，效果与value一样，但不能与value属性同时存在 */
public class SpringConfiguration {
}
