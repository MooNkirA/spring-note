package com.moon.spring.bean;

import lombok.Data;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-5 22:26
 * @description
 */
@Data
public class DecoratorBean {

    private String username;
    private String password;
    private String age;
    private String sex;

    public DecoratorBean(String age, String sex) {
        this.age = age;
        this.sex = sex;
    }

}
