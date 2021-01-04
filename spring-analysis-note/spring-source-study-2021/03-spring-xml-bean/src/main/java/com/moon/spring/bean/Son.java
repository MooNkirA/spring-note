package com.moon.spring.bean;

import lombok.Data;

/**
 * 测试<bean>标签的抽象属性parent，设置继承关系
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 12:39
 * @description
 */
@Data
public class Son {

    private String username;
    private int age;

}
