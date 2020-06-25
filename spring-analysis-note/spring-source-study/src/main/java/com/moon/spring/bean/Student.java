package com.moon.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @author MoonZero
 * @version 1.0
 * @date 2019-12-15 12:18
 * @description
 */
@Component
public class Student {

    private String userName = "moon";

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
