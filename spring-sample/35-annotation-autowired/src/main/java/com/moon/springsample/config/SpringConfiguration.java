package com.moon.springsample.config;

import com.moon.springsample.service.UserService;
import com.moon.springsample.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring项目的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 08:50
 * @description
 */
@Configuration
// 配置包扫描
@ComponentScan("com.moon.springsample")
public class SpringConfiguration {

    /**
     * 手动创建bean实例，注册到容器中
     *
     * @return
     */
    // @Bean // 用于测试两个同类型的实例测试到容器中
    @Bean("userService")
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    /**
     * 手动创建同类型的bean实例，注册到容器中
     *
     * @return
     */
    // @Bean
    @Bean("userServiceOther")
    public UserService createUserServiceOther() {
        return new UserServiceImpl();
    }

}