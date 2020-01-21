package com.moon.spring.bean;

import com.moon.spring.beanDefinition.MoonService;
import lombok.Data;

/**
 * 标注自定义注解的类 - 用于测试使用spring框架DI自动注入
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-11 16:04
 * @description
 */
@Data
@MoonService
public class CustomAnnotationClass {

    private String name = "moon";

}
