package com.moon.springsample.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 基于 InitializingBean & DisposableBean 接口实现初始化与销毁
 * 除了 @PostConstruct & @PreDestroy、 @Bean 注解方式指定初始化和销毁方法外，Spring还提供了和初始化，销毁相对应的接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 22:11
 * @description
 */
// @Service
public class UserService implements InitializingBean, DisposableBean {

    public UserService() {
        System.out.println("UserService 构造方法执行了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService 实现 InitializingBean 接口实现初始化的 afterPropertiesSet() 方法执行了");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserService 实现 DisposableBean 接口实现销毁的 destroy() 方法执行了");
    }
}
