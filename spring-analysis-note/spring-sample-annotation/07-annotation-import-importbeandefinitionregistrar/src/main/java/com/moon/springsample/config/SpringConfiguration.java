package com.moon.springsample.config;

import com.moon.springsample.registrar.CustomImportDefinitionRegistrar;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 21:31
 * @description
 */
@Configuration
// 配置扫描的包路径
@ComponentScan("com.moon.springsample.service.impl")
// 导入自定义 bean 定义注册器
@Import(CustomImportDefinitionRegistrar.class)
public class SpringConfiguration {
}
