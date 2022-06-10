package com.moon.springsample.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 测试实体
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-05-16 15:35
 * @description
 */
@Component
@Data
public class Cat {

    private String name;
    private int age;
    private String color;

}
