package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring项目的配置类，用于代替传统的xml配置文件
 * <p>没有applicationContext.xml，就没法在xml中配置spring创建容器要扫描的包了。</p>
 * <p>那么可以创建一些类，通过注解配置到ioc容器中也无法实现了。此时就可以使用此注解来代替spring的配置文件。</p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-31 23:33
 * @description
 */
@Configuration("springConfiguration") /* 标识当前类为配置类 */
@ComponentScan("com.moon.springsample") /* 配置开启包扫描 */
// @Import(Xxxxx.class) /* 通过@Import注解导入其他的配置类 */
// @PropertySource("classpath:xxxx.properties") /* 通过@PropertySource注解导入配置文件，如.properties、.xml等 */
public class SpringConfiguration {
}
