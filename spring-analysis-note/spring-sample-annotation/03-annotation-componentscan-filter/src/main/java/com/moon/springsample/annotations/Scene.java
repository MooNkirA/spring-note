package com.moon.springsample.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标识不同场景的注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-25 23:49
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scene {

    /**
     * 用于指定场景的名称
     *
     * @return
     */
    String value() default "";

}
