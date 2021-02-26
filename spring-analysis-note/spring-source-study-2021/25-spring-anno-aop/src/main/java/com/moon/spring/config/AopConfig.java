package com.moon.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置开启Spring容器的AOP注解支持
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-25 22:24
 * @description
 */
@Configuration
/* 注解的方式开启AOP注解支持，相当于xml配置文件中的 <aop:aspectj-autoproxy/> 标签 */
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = true)
public class AopConfig {
}
