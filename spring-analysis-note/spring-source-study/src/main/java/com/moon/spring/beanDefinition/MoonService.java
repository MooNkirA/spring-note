package com.moon.spring.beanDefinition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 - 用于测试spring对自定义注解扫描测试
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-11 15:52
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MoonService {

    String value() default "";

}
