package com.moon.spring.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试自定义bean作用域
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-6-7 09:49
 * @description
 */
@Component
@Scope("MooNkirAScope")
public class CustomScopeBean {

    private String id;
    private String name;

    public CustomScopeBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
