package com.moon.springsample.config;

import com.moon.springsample.importselector.CustomImportSelector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 14:03
 * @description
 */
@Configuration
// 指定包扫描路径
@ComponentScan("com.moon.springsample.service.utils") // 改造自定义导入器，将扫描包路径也定义在配置文件中，同时兼容注解配置的方式
// 导入自定义ImportSelector导入器
@Import(CustomImportSelector.class)
public class SpringConfiguration {
}
