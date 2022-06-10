package com.moon.springsample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 10:36
 * @description
 */
@Configuration
/*
 * 使用@Import注解引入其他配置类，被引入的类上可以不再使用`@Configuration`，`@Component`等注解的支撑，spring ioc也可以将引入类加载到ioc容器中。
 *  注意：通过@Import注解引入的类，该类加载到spring ioc容器的唯一标识（beanName）与使用`@Configuration`，`@Component`等注解的默认标识的命名方式不一样
 *      使用`@Configuration`，`@Component`等注解加载ioc容器，如果不指定名称，则以类名称且首字母小写，作为容器中唯一标识与其对象实例进行映射
 *      而使用@Import注解引入的类，则以引入类的全限定名做为容器中唯一标识，并与其对象实例进行映射
 */
@Import({JdbcConfig.class})
public class SpringConfiguration {
}
