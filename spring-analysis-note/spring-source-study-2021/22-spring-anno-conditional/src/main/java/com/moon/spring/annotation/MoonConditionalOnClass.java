package com.moon.spring.annotation;

import com.moon.spring.condition.CustomOnClassCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义条件实例化注解 - 当项目存在指定的Class文件时，才实例化注解标识的类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 16:22
 * @description
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(CustomOnClassCondition.class)
public @interface MoonConditionalOnClass {

    Class<?>[] value() default {};

    String[] name() default {};

}
