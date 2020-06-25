package com.moon.spring.beanlifecycle.config;

import com.moon.spring.beanlifecycle.bean.Fish;
import com.moon.spring.beanlifecycle.processor.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MoonZero
 * @version 1.0
 * @date 2019-9-28 10:21
 * @description
 */
@Configuration
public class WebConfig {

    /*@Bean
    public Moon moon() {
        return new Moon();
    }*/

    @Bean
    public Fish fish(){
        return new Fish();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
