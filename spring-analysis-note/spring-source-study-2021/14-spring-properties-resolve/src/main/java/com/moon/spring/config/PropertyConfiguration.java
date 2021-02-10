package com.moon.spring.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 通过xml配置，指定读取的properties文件，
 * 继承spring的PropertySourcesPlaceholderConfigurer类继承spring框架的`PropertySourcesPlaceholderConfigurer`类
 * 或者 `PropertyPlaceholderConfigurer`类（*此类已过时，在spring 5.2版本后，使用前面那个类*），实现读取配置文件并注入属性值
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-9 14:56
 * @description
 */
// public class PropertyConfiguration extends PropertyPlaceholderConfigurer {
public class PropertyConfiguration extends PropertySourcesPlaceholderConfigurer {
}
