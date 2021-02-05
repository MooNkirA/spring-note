package com.moon.spring.config;

import com.moon.spring.aware.CustomImportAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring 项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-3 15:26
 * @description
 */
@Configuration
@PropertySource("classpath:application.properties")
@Import(CustomImportAware.class)
public class SpringConfiguration {
}
