package com.moonzero.dao.impl;

import com.moonzero.dao.IUserDao;

public class UserDaoImpl implements IUserDao {

    @Override
    public void add(String name) {
        System.out.println(name + "===被执行了。");
    }
}
