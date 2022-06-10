package com.moon.springmvc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * 用户实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-18 10:52
 * @description
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String gender;
    /* 日期转换器，需要在配置类中标识@EnableWebMvc注解开启SpringMVC注解的支持 */
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("age=" + age)
                .add("gender='" + gender + "'")
                .add("birthday=" + birthday)
                .toString();
    }
}
