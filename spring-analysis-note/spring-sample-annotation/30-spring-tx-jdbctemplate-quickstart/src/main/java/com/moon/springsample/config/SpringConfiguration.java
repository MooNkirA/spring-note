package com.moon.springsample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring项目配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-13 11:00
 * @description
 */
@Configuration
// 导入其他模块的配置类
@Import({JdbcConfig.class})
// 导入配置文件（支持类路径方式）
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {
}
