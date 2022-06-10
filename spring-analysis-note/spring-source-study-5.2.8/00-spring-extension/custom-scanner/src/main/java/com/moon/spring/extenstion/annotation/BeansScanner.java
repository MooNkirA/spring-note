package com.moon.spring.extenstion.annotation;

import com.moon.spring.extenstion.registrar.BeansScannerRegistrar;
import com.moon.spring.extenstion.registrar.CustomBeanDefinitionScanner;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义实体扫描注解，用于指定扫描的包路径
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 13:49
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BeansScannerRegistrar.class) // 通过@Import注解导入自定义包扫描的处理逻辑类
public @interface BeansScanner {

    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

    Class<? extends Annotation> annotationClass() default Annotation.class;

}
