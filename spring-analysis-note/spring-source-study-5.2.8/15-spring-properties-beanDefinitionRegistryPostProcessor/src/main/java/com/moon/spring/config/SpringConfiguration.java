package com.moon.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 14:09
 * @description
 */
@Configuration
/*
 * @PropertySource注解用于引入配置文件，与xml的配置
 * <context:property-placeholder location="classpath:application.properties"/>
 * 作用一样，也可实现将注入属性值为占位符时替换成相应的值
 *
 * 注：但此注解在使用@Value(${xx.xx})的情况，能成功解析占位符并替换成配置文件中的值
 * xml配置<property name="abc" value="${xx.xx}"/> 却无法解析占位符
 */
@PropertySource("classpath:application.properties")
public class SpringConfiguration {
}
