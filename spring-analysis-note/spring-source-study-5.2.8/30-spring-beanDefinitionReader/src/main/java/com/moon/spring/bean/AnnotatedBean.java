package com.moon.spring.bean;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.Scope;

/**
 * 用于测试 AnnotatedBeanDefinitionReader 注解的 BeanDefinition读取器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-10-06 16:36
 * @description
 */
// @Conditional
@Scope
@Lazy
@Primary
@DependsOn
@Role(BeanDefinition.ROLE_APPLICATION)
@Description("这是一个类的描述")
@Data
public class AnnotatedBean {

    private String name;
    private int score;

}
