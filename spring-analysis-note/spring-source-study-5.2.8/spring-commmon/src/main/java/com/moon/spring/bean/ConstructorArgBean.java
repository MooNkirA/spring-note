package com.moon.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于测试 <bean> 子标签 <constructor-arg>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-3 22:34
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstructorArgBean {

    private String username;
    private String password;

}
