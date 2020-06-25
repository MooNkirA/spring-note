package com.moon.spring.bean;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class ConstructorArgBean {

    private String username;

    private String password;

    public ConstructorArgBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConstructorArgBean.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .toString();
    }

}
