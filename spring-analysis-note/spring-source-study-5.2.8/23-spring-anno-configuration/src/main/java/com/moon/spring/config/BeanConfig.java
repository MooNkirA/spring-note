package com.moon.spring.config;

import com.moon.spring.bean.Bird;
import com.moon.spring.bean.Cat;
import com.moon.spring.bean.MotherCat;
import com.moon.spring.bean.MyFactoryBean;
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
@Configuration
// @Component
public class BeanConfig {

    @Bean
    public Cat cat() {
        return new Cat();
    }

    @Bean
    public MotherCat motherCat() throws Exception {
        MotherCat motherCat = new MotherCat();
        // 对标识了@Bean注解的方法进行调用
        motherCat.setCat(this.cat());

        // 调用FactoryBean接口实例的getObject方法
        Bird bird = this.myFactoryBean().getObject();
        System.out.println("MyFactoryBean getObject : " + bird.hashCode());

        return motherCat;
    }

    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }

}
