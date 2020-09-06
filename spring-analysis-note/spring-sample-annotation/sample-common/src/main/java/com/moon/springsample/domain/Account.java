package com.moon.springsample.domain;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 账户实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-5 15:20
 * @description
 */
public class Account implements Serializable {
    
    private Integer id;
    private String name;
    private Double money;

    public Account() {
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("money=" + money)
                .toString();
    }
}
