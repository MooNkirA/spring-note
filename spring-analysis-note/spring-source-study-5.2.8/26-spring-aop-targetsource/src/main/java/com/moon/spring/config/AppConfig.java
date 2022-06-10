package com.moon.spring.config;

import com.moon.spring.constant.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-3-6 16:15
 * @description
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan(Constants.BASE_PACKAGES)
public class AppConfig {
}
