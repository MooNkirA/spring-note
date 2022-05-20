package com.moon.springsample.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 测试 request 作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-19 11:12
 * @description
 */
@Scope(WebApplicationContext.SCOPE_REQUEST) // 或 "request" 字符串
@Component
public class RequestScopeBean {

    @PostConstruct
    public void init() {
        System.out.println("RequestScopeBean postConstruct...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("RequestScopeBean destroy...");
    }

}
