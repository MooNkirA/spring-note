package com.moon.springsample.sevice.impl;

import com.moon.springsample.domain.User;
import com.moon.springsample.sevice.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-6 17:40
 * @description
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser(User user, String id) {
        System.out.println("UserServiceImpl.saveUser()执行了保存用户" + user.toString());
        // int a = 1 / 0; // 模拟异常
    }

    @Override
    public User findById(String id) {
        User user = new User();
        user.setId(id);
        user.setUsername("长泽雅美");
        System.out.println("准备返回的User对象是: " + user.toString());
        int a = 1 / 0; // 模拟异常
        return user;
    }
}
