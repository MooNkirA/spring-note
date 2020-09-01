package com.moon.springsample.annotation;

import org.springframework.context.annotation.Bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用@Bean注解作为元注解，自定义注解实现@Bean注解相同的功能
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 10:12
 * @description
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Bean
public @interface MyBean {
}