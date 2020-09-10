package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

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
// 开启spring注解aop配置的支持（注：此注解是采用运行期增强），与@EnableLoadTimeWeaving不会同时使用
// @EnableAspectJAutoProxy
// 开启类加载时期的增强（注：此注解是采用加载器时期进行织入，执行目标类方法时增强）
@EnableLoadTimeWeaving
public class SpringConfiguration {
}
