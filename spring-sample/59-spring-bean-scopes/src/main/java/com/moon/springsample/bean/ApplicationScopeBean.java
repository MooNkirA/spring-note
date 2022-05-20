package com.moon.springsample.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 测试 application 作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 11:12
 * @description
 */
@Scope(WebApplicationContext.SCOPE_APPLICATION) // 或 "application" 字符串
@Component
public class ApplicationScopeBean {

    @PostConstruct
    public void init() {
        System.out.println("ApplicationScopeBean postConstruct...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ApplicationScopeBean destroy...");
    }

}
