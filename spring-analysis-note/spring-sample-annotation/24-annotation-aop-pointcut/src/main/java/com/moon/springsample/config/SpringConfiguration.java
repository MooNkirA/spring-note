package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring项目配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:51
 * @description
 */
@Configuration
@ComponentScan("com.moon.springsample")
// 开启spring注解aop配置的支持
@EnableAspectJAutoProxy
public class SpringConfiguration {
}
