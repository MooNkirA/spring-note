package com.moon.spring.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用于测试手动注册到spring容器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-11 17:12
 * @description
 */
@Getter
@Setter
@ToString
public class Cat {

    private String name;
    private String color;

}
