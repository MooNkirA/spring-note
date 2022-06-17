package com.moon.springsample.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于模拟 Spring 的 @EventListener 注解实现监听方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-16 16:19
 * @description
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomListener {
}
