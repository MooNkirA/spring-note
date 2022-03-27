package com.moon.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于测试ImportSelector与ImportBeanDefinitionRegistrar接口
 * 在使用@Import注解导入后获取导入类的其他注解信息
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-16 09:23
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface CustomAnnotation {

    String[] value() default {};

}
