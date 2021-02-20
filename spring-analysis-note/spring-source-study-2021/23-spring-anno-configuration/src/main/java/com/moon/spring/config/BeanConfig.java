package com.moon.spring.config;

import com.moon.spring.bean.Cat;
import com.moon.spring.bean.MotherCat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 测试 @Configuration 与 @Component 注解的区别
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-20 21:23
 * @description
 */
// @Configuration
@Component
public class BeanConfig {

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public MotherCat motherCat() {
        MotherCat motherCat = new MotherCat();
        // 对标识了@Bean注解的方法进行调用
        motherCat.setCat(this.cat());
        return motherCat;
    }

}
