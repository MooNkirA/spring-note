package com.moon.springsample.custom;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * 自定义BeanName生成规则实现类，需实现spring框架的BeanNameGenerator接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-9 23:01
 * @description
 */
public class CustomBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        // 0. 定义返回的beanName
        String beanName = null;

        // 1. 判断当前BeanDefinition对象是否为注解
        if (definition instanceof AnnotatedBeanDefinition) {
            // 2. 将BeanDefinition对象强转成注解的BeanDefinition对象
            AnnotatedBeanDefinition abd = (AnnotatedBeanDefinition) definition;
            // 3. 通过BeanDefinition对象获取注解的元信息（AnnotationMetadata）
            AnnotationMetadata amd = abd.getMetadata();
            // 4. 获取元信息中所有注解的Set集合
            Set<String> annotationTypes = amd.getAnnotationTypes();
            // 5. 遍历AnnotationType的集合
            for (String type : annotationTypes) {
                AnnotationAttributes attributes = AnnotationAttributes
                        .fromMap(amd.getAnnotationAttributes(type, false));
            }
        }

        return beanName;
    }

}
