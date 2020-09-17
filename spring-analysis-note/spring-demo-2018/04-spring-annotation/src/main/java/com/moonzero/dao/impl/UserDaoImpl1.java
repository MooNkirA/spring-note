package com.moonzero.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.moonzero.dao.IUserDao;

/**
 * 用户持久层实现类1111
 *
 * @author MoonZero
 */
@Component(value = "userDao1")
@Scope(value = "singleton")
public class UserDaoImpl1 implements IUserDao {

    @Override
    public void addUser(String name) {
        System.out.println(name + "====执行了用户持久层实现类1111111！");
    }
}
