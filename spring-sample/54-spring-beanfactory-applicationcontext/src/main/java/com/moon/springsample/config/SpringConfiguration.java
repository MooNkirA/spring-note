package com.moon.springsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-14 21:55
 * @description
 */
@Configuration
@ComponentScan("com.moon.springsample") // 配置包扫描
public class SpringConfiguration {

    /**
     * 注册国际化的 bean，MessageSource 实例，bean名称必须为：messageSource
     * @return
     */
    @Bean
    public ResourceBundleMessageSource messageSource(){
        // 创建 MessageSource 的实现类 ResourceBundleMessageSource
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        /*
         * 指定国际化配置文件的位置，格式：路径/文件名称，注意文件名称不包含语言后缀这部分。
         * 即：messages_zh.properties 配置的名称为 messages
         */
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
