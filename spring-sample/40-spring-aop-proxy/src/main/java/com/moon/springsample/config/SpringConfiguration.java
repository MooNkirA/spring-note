package com.moon.springsample.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 21:37
 * @description
 */
@Configuration
// 配置包扫描
@ComponentScan("com.moon.springsample")
// 导入其他模块的配置类
@Import(JdbcConfig.class)
// 导入yml格式的配置文件，需要编写一个PropertySourceFactory的实现类，实现yml文件的解析
@PropertySource(value = {"classpath:jdbc.properties"})
public class SpringConfiguration {
}
