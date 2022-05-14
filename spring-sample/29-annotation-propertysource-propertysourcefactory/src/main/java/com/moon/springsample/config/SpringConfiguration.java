package com.moon.springsample.config;

import com.moon.springsample.propertysource.factory.CustomPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-30 10:24
 * @description
 */
@Configuration
// 导入其他模块的配置类
@Import(JdbcConfig.class)
// 导入yml格式的配置文件，默认情况下此注解只能解析properties文件和xml文件。yaml（yml）文件解析就会报错。需要一个PropertySourceFactory的实现类，实现yml文件的解析
@PropertySource(value = {"classpath:jdbc.yml"}, factory = CustomPropertySourceFactory.class)
public class SpringConfiguration {
}
