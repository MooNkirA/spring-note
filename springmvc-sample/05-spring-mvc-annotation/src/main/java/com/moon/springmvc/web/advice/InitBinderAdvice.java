package com.moon.springmvc.web.advice;

import com.moon.springmvc.web.converters.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 注解使用示例通知控制器增强 - @ControllerAdvice注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 17:17
 * @description
 */
/* 标识当前类为增强通知类，可以增强指定或者所有控制类（配置包扫描时所包含的范围） */
/*
 * value属性basePackages属性作用一样，可以指定哪些包下的控制器进行增强
 *      示例：增强com.moon.springmvc.web包及其子包下的所有控制类
 */
// @ControllerAdvice(basePackages = {"com.moon.springmvc.web"})

/*
 * basePackageClasses属性：用于指定特定的类型，给该类型所在的包及其子包的所有类提供增强
 *      示例：给BasicController所在的包及其子包的所有类提供增强
 */
// @ControllerAdvice(basePackageClasses = BasicController.class)

/*
 * assignableTypes属性：用于指定特定的类型提供增强
 *      示例：给InitBinderController类提供增强
 */
// @ControllerAdvice(basePackageClasses = InitBinderController.class)

/*
 * annotations属性：用于指定给特定注解提供增强
 *      示例：给标识了自定义@CustomAnnotation注解的控制类提供增强
 */
// @ControllerAdvice(annotations = CustomAnnotation.class)

@ControllerAdvice // 增强所有能扫描到的控制类
public class InitBinderAdvice {

    /* 配置@InitBinder注解使用，对所有控制进行请求数据绑定的初始化 */
    /*@InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // 初始化请求参数中日期字符串转成日期Date对象
        webDataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }*/

    // *********************************************************
    //                 使用自定义类型转换器
    // *********************************************************
    // 注入自定义类型转换器
    @Autowired
    private StringToDateConverter stringToDateConverter;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // 1. 获取转换服务对象
        ConversionService conversionService = webDataBinder.getConversionService();
        // 2. 判断conversionService是否为GenericConversionService类型
        if (conversionService instanceof GenericConversionService) {
            // 3. 强转
            GenericConversionService genericConversionService = (GenericConversionService) conversionService;
            // 4. 添加类型转换器
            genericConversionService.addConverter(stringToDateConverter);
        }
    }

}
