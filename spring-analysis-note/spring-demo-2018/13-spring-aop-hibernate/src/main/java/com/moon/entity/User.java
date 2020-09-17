package com.moon.entity;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author MoonZero
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String gender;

    public User() {
    }

    public User(Integer id, String name, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", gender=" + gender + "]";
    }

}
