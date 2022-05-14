package com.moon.springsample.service.impl;

import com.moon.springsample.service.UserService;

/**
 * 用户业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 08:54
 * @description
 */
public class UserServiceImpl implements UserService {

    /**
     * 模拟保存用户
     */
    @Override
    public void saveUser() {
        System.out.println("UserServiceImpl.saveUser()方法执行，保存用户数据");
    }

}
