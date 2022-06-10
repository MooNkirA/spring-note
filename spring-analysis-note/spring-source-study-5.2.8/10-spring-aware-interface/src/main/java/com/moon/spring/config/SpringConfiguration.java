package com.moon.spring.config;

import com.moon.spring.aware.CustomImportAware;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

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
@Data
public class SpringConfiguration {

    private String jdbcUrl;
    private Class<?>[] classes;
    private List<String> configList;

    public String getConfig(String name) {
        return "我是配置类的getConfig方法 :: " + name;
    }

}
