package com.moon.springsample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-1 11:18
 * @description
 */
@Configuration
// 引入其他配置类
@Import(JdbcConfig.class)
// 加载配置文件
@PropertySource({"classpath:jdbc.properties"})
public class SpringConfiguration {
}
