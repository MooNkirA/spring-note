package com.moon.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.Arrays;

/**
 * 配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-06-10 10:23
 * @description
 */
@Configuration
@ComponentScan("com.moon.springmvc")
public class SpringMvcConfig {

    /* 创建异常处理器 */
    @Bean
    public ExceptionHandlerExceptionResolver resolver() {
        ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
        resolver.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        return resolver;
    }
}
