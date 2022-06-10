package com.moon.springsample.config;

import com.moon.springsample.bean.CustomBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 17:08
 * @description
 */
@Configuration
// 配置包扫描
@ComponentScan("com.moon.springsample")
public class SpringConfiguration {

    public SpringConfiguration() {
        System.out.println("SpringConfiguration 类构造方法执行");
    }

    /* 通过@Bean 注解方式创建对象并注册到IOC容器中，实现initMethod与destroyMethod方法 */
    // @Bean(value = "customBean", initMethod = "init", destroyMethod = "destory")
    // @Scope("prototype") // 设置多例，用于测试生命周期
    public CustomBean createCustomBean() {
        return new CustomBean();
    }

}
