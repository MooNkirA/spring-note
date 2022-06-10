package com.moon.springsample.service.impl;

import com.moon.springsample.domain.User;
import com.moon.springsample.service.UserService;
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
    public void saveUser(User user) {
        try {
            Thread.sleep(1500);
            System.out.println("UserServiceImpl.saveUser()执行了保存用户" + user.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
