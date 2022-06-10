package com.moon.spring.annotation;

import com.moon.spring.condition.CustomOnPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义条件实例化注解 - 检查指定的属性是否具有特定值时，才实例化注解标识的类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-17 20:45
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(CustomOnPropertyCondition.class)
public @interface MoonConditionalOnProperty {

    String[] value() default {};

    String[] name() default {};
}
