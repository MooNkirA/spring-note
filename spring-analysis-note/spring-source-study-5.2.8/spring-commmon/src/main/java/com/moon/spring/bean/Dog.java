package com.moon.spring.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 注册bean到spring容器方式2: 使用@Component等注解配置
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-17 11:32
 * @description
 */
@Data
@Component
public class Dog {

    private String name;
    private int age;

}
