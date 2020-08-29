package com.moon.springsample.service.impl;

import com.moon.springsample.service.UserService;

/**
 * 用户业务层实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-29 14:01
 * @description
 */
public class UserServiceImpl implements UserService {

    /**
     * 模拟实现保存用户的方法
     */
    @Override
    public void saveUser() {
        System.out.println("UserServiceImpl.saveUser()方法执行，保存用户数据");
    }

}
