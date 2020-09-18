package com.moon.springmvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于测试 @ControllerAdvice 的 指定特定注解增强的属性 annotations
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 22:14
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomAnnotation {

    String value() default "";

}
