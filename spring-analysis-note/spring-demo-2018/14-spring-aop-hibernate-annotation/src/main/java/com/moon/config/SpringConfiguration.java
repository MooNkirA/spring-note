package com.moon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 使用纯注解的配置类
 *
 * @author MoonZero
 */
// 指定当前类为配置类
@Configuration
// 用于指定spring在初始化容器时要扫描的包
@ComponentScan("com.moon")
// 开启对象注解AOP支持
@EnableAspectJAutoProxy
public class SpringConfiguration {

}
