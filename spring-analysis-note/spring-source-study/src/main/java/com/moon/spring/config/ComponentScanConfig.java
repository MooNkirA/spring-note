package com.moon.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 测试 @ComponentScan 注解配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-7 13:41
 * @description
 */
// @Configuration
@ComponentScan(basePackages = {"com.moon.spring"})
// @Import(DestroyDemoBean.class)
public class ComponentScanConfig {

}
