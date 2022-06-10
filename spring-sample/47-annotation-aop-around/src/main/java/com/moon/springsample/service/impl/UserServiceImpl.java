package com.moon.springsample.service.impl;

import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void saveUser(User user) {
        System.out.println("UserServiceImpl.saveUser()执行了保存用户" + user.toString());
    }

    @Override
    public User findById(String id) {
        User user = new User();
        user.setId(id);
        user.setUsername("长泽雅美");
        return user;
    }

    @Override
    public void update(User user) {
        System.out.println("执行了更新用户" + user);
    }

    @Override
    public void delete(String id) {
        System.out.println("执行了删除用户" + id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setUsername("天锁斩月" + i);
            user.setNickname("MooNkirA" + i);
            users.add(user);
        }
        return users;
    }

}
